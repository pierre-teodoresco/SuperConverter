package fr.pteodoresco.superconverter;

import android.content.Context;

public abstract class Converter {
    protected float rate = -1.f;
    protected ConverterListener listener;

    public abstract void init(Context context);

    public float getRate() {
        return this.rate;
    }

    public float convert(float value) {
        return value * this.rate;
    }

    public void setListener(ConverterListener listener) {
        this.listener = listener;
    }
}
