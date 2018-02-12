/**
 * A transmissionBox represent the transmission within a single car.
 */
public class TransmissionBox {
  private int currentSpeed;
  private int threshold1;
  private int threshold2;
  private int threshold3;
  private int threshold4;
  private int gear; // Gear is ranged from 1 to 5

  /**
   * construct a TransmissionBox object with parameter currentSpeed, threshold1, threshold2,
   * threshold3, threshold4.
   *
   * @param currentSpeed represent the current speed of the car.
   * @param threshold1   represent the first level threshold of changing the gear.
   * @param threshold2   represent the second level threshold of changing the gear.
   * @param threshold3   represent the third level threshold of changing the gear.
   * @param threshold4   represent the fourth level threshold of changing the gear.
   * @throws IllegalArgumentException throw a IllegalArgumentException if the input speed or
   *                                  threshold is less than 0.
   */
  public TransmissionBox(int currentSpeed, int threshold1, int threshold2, int threshold3,
                         int threshold4) throws IllegalArgumentException {
    if (currentSpeed < 0 || threshold1 < 0 || threshold2 <= threshold1 || threshold3 <= threshold2
            || threshold4 <= threshold3) {
      throw new IllegalArgumentException("Invalid Input!");
    }

    this.currentSpeed = currentSpeed;
    this.threshold1 = threshold1;
    this.threshold2 = threshold2;
    this.threshold3 = threshold3;
    this.threshold4 = threshold4;
    this.gear = getGearStatus();

  }

  /**
   * Get the current gear level.
   *
   * @return the current gear relating to the speed level.
   */
  public int getGearStatus() {
    int gear;
    if (this.currentSpeed <= this.threshold1) {
      gear = 1;
    } else if (this.currentSpeed <= this.threshold2) {
      gear = 2;
    } else if (this.currentSpeed <= this.threshold3) {
      gear = 3;
    } else if (this.currentSpeed <= this.threshold4) {
      gear = 4;
    } else {
      gear = 5;
    }
    return gear;
  }

  /**
   * Get the current speed of the car.
   *
   * @return the current speed.
   */
  public int getSpeedStatus() {
    return this.currentSpeed;
  }

  /**
   * Return a string representation of the TransmissionBox object.
   *
   * @return a string represent the transmission box object.
   */
  @Override
  public String toString() {
    return String.format("Current Speed:%d. Gear Level:%d.", this.currentSpeed, this.gear);
  }

  /**
   * Increase the speed by 2 in the transmission box.
   *
   * @return the new transmission box with increased speed.
   */
  public TransmissionBox increaseSpeed() {
    return new TransmissionBox(this.currentSpeed + 2, this.threshold1,
            this.threshold2, this.threshold3, this.threshold4);
  }

  /**
   * Decrease the speed by 2 in the transmission box.
   *
   * @return the new transmission box with decreased speed.
   */
  public TransmissionBox decreaseSpeed() {
    //when speed is decreased less than 0, set speed to zero.
    int speed = (this.currentSpeed - 2 < 0) ? 0 : (this.currentSpeed - 2);
    return new TransmissionBox(speed, this.threshold1,
            this.threshold2, this.threshold3, this.threshold4);
  }
}
