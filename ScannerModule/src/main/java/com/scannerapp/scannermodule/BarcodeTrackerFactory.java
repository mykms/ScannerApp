package com.scannerapp.scannermodule;

import com.google.android.gms.vision.MultiProcessor;
import com.google.android.gms.vision.Tracker;
import com.google.android.gms.vision.barcode.Barcode;

/*
Фабрика для создания трекера и связанного с ним графического изображения, связанного с новым штрих-кодом.
Мультипроцессор использует эту фабрику для создания трекеров по штрих-кодам по мере необходимости - по одному для каждого штрих-кода.
 */
public class BarcodeTrackerFactory implements BarcodeGraphic.BarcodeResult, MultiProcessor.Factory<Barcode> {
    private GraphicOverlay<BarcodeGraphic> mGraphicOverlay;
    private BarcodeGraphic graphic = null;
    private BarcodeResultFactory barcodeResultFactory;

    public interface BarcodeResultFactory {
        void getBarcodeResultFactory(Barcode _barcode);
    }

    public BarcodeTrackerFactory(GraphicOverlay<BarcodeGraphic> barcodeGraphicOverlay) {
        mGraphicOverlay = barcodeGraphicOverlay;
    }

    public void registerCallBackFactory(BarcodeResultFactory callback){
        this.barcodeResultFactory = callback;
    }

    @Override
    public void getBarcodeResult(Barcode _mBarcode) {
        //
    }

    @Override
    public Tracker<Barcode> create(Barcode barcode) {
        graphic = new BarcodeGraphic(mGraphicOverlay);
        graphic.registerCallBack(this, barcodeResultFactory);
        //barcodeResultFactory.getBarcodeResultFactory();
        return new BarcodeGraphicTracker(mGraphicOverlay, graphic);
    }
}