package ent.processors;

import java.util.ArrayList;

public interface BaseProcessor {
    ArrayList<Float> uzsToOther(Float value);

    ArrayList<Float> usdToOther(Float value);

    ArrayList<Float> eurToOther(Float value);

    ArrayList<Float> rubToOther(Float value);

    ArrayList<Float> currency();
}
