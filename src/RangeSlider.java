import javax.swing.JSlider;

public class RangeSlider extends JSlider {
	public RangeSlider() {
		initSlider();
	}

	public RangeSlider(int min, int max) {
		super(min, max);
		initSlider();
	}

	private void initSlider() {
		setOrientation(HORIZONTAL);
	}

	public void updateUI() {
		setUI(new RangeSliderUI(this));
		updateLabelUIs();
	}

	public int getValue() {
		return super.getValue();
	}

	public void setValue(int value) {
		int oldValue = getValue();
		if (oldValue == value) {
			return;
		}
		int oldExtent = getExtent();
		int newValue = Math.min(Math.max(getMinimum(), value), oldValue + oldExtent);
		int newExtent = oldExtent + oldValue - newValue;
		getModel().setRangeProperties(newValue, newExtent, getMinimum(), getMaximum(), getValueIsAdjusting());
	}

	public int getUpperValue() {
		return getValue() + getExtent();
	}

	public void setUpperValue(int value) {
		int lowerValue = getValue();
		int newExtent = Math.min(Math.max(0, value - lowerValue), getMaximum() - lowerValue);
		setExtent(newExtent);
	}

}
