package Adaptee;

public class WeighingScaleImpl implements WeighingScale{
    int weight;

    public WeighingScaleImpl(int weight) {
        this.weight = weight;
    }

    @Override
    public int getWeightInpounds() {
        return weight;
    }
}
