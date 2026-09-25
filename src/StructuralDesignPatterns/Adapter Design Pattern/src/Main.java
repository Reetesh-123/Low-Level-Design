import Adaptee.WeighingScale;
import Adaptee.WeighingScaleImpl;
import Adaptor.WeighingScaleAdaptor;
import Adaptor.WeighingScaleAdaptorImpl;

void main() {
    // Adaptee: existing scale that only reports weight in pounds
    WeighingScale poundScale = new WeighingScaleImpl(150);
    IO.println("Weight from adaptee (lbs): " + poundScale.getWeightInpounds());

    // Adaptor: wraps the pound scale so clients can ask for kilograms
    WeighingScaleAdaptor kgScale = new WeighingScaleAdaptorImpl(poundScale);
    IO.println("Weight from adaptor (kg): " + kgScale.getWeightInKG());

    // Client code depends only on the target interface
    int[] weightsInPounds = {100, 220, 0};
    for (int lbs : weightsInPounds) {
        WeighingScaleAdaptor adaptor = new WeighingScaleAdaptorImpl(new WeighingScaleImpl(lbs));
        IO.println(lbs + " lbs -> " + adaptor.getWeightInKG() + " kg");
    }
}
