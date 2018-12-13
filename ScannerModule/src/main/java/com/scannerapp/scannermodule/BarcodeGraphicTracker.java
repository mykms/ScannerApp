package com.scannerapp.scannermodule;

import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Tracker;
import com.google.android.gms.vision.barcode.Barcode;

/* Generic tracker, который используется для отслеживания или чтения штрих-кода (и может использоваться для любого предмета).
    Он используется для вновь обнаруженных элементов, добавления графического представления на оверлей,
    обновить графику по мере изменения элемента и удалить графику, когда элемент уходит.
    */
class BarcodeGraphicTracker extends Tracker<Barcode> {
    private GraphicOverlay<BarcodeGraphic> mOverlay;
    private BarcodeGraphic mGraphic;

    BarcodeGraphicTracker(GraphicOverlay<BarcodeGraphic> overlay, BarcodeGraphic graphic) {
        mOverlay = overlay;
        mGraphic = graphic;
    }

    // Начинает отслеживать обнаруженный экземпляр элемента внутри наложения элемента.
    @Override
    public void onNewItem(int id, Barcode item) {
        mGraphic.setId(id);
    }

    // Обновляет положение / характеристики элемента в оверлее.
    @Override
    public void onUpdate(Detector.Detections<Barcode> detectionResults, Barcode item) {
        mOverlay.add(mGraphic);
        mGraphic.updateItem(item);
    }

    // Скрыть графику, когда соответствующий объект не был обнаружен. Это может случиться для промежуточных кадров временно, например, если объект был временно заблокирован из представления.
    @Override
    public void onMissing(Detector.Detections<Barcode> detectionResults) {
        mOverlay.remove(mGraphic);
    }

    // Вызывается, когда предмет считается ушедшим навсегда. Удалите графическую аннотацию с наложения.
    @Override
    public void onDone() {
        mOverlay.remove(mGraphic);
    }
}
