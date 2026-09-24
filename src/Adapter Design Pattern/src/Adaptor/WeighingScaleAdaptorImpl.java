package Adaptor;

import Adaptee.WeighingScale;

public class WeighingScaleAdaptorImpl implements WeighingScaleAdaptor{

    WeighingScale weighingScale;

    public WeighingScaleAdaptorImpl(WeighingScale weighingScale) {
        this.weighingScale = weighingScale;
    }

    @Override
    public int getWeightInKG() {
        return (int)(0.453592 * weighingScale.getWeightInpounds());
    }
}
