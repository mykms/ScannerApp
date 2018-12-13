package com.scannerapp.scannermodule;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.google.android.gms.vision.barcode.Barcode;

//Графический экземпляр для отображения позиции, размера и идентификатора штрих-кода в соответствующем графическом оверлейном представлении.
public class BarcodeGraphic extends GraphicOverlay.Graphic {
    public interface BarcodeResult {
        void getBarcodeResult(Barcode _mBarcode);
    }

    private int mId;
    private static int mCurrentColorIndex = 0;
    private Paint mRectPaint;
    private Paint mTextPaint;
    private volatile Barcode mBarcode;
    BarcodeResult barcodeResult = null;
    BarcodeTrackerFactory.BarcodeResultFactory barcodeResultFactory;

    private static final int COLOR_CHOICES[] = {
            Color.BLUE,
            Color.CYAN,
            Color.GREEN
    };

    BarcodeGraphic(GraphicOverlay overlay) {
        super(overlay);

        mCurrentColorIndex = (mCurrentColorIndex + 1) % COLOR_CHOICES.length;
        final int selectedColor = COLOR_CHOICES[mCurrentColorIndex];

        mRectPaint = new Paint();
        mRectPaint.setColor(selectedColor);
        mRectPaint.setStyle(Paint.Style.STROKE);
        mRectPaint.setStrokeWidth(4.0f);

        mTextPaint = new Paint();
        mTextPaint.setColor(selectedColor);
        mTextPaint.setTextSize(36.0f);
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    void registerCallBack(BarcodeResult callback, BarcodeTrackerFactory.BarcodeResultFactory resultFactory){
        this.barcodeResult = callback;
        barcodeResultFactory = resultFactory;
    }

    public Barcode getBarcode() {
        return mBarcode;
    }

    // Обновляет экземпляр штрих-кода от обнаружения самого последнего кадра. Недействительность соответствующих частей наложения, чтобы вызвать перерисовку.
    void updateItem(Barcode barcode) {
        mBarcode = barcode;
        postInvalidate();
        barcodeResult.getBarcodeResult(mBarcode);               // Для получения результата на активити
        barcodeResultFactory.getBarcodeResultFactory(mBarcode); // Для получения результата на активити
    }

    // Рисует аннотации штрих-кодов для позиции, размера и необработанного значения на поставляемом холсте.
    @Override
    public void draw(Canvas canvas) {
        Barcode barcode = mBarcode;
        if (barcode == null) {
            return;
        }

        // Рисует прямоугольник вокруг бар-кода
        RectF rect = new RectF(barcode.getBoundingBox());
        rect.left = translateX(rect.left);
        rect.top = translateY(rect.top);
        rect.right = translateX(rect.right);
        rect.bottom = translateY(rect.bottom);
        canvas.drawRect(rect, mRectPaint);

        // Рисует надпись (внизу), если штрих-код обнуржен
        canvas.drawText(barcode.rawValue, rect.left, rect.bottom, mTextPaint);
    }
}
