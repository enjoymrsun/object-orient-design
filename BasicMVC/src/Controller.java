public class Controller implements IActionListener {
  private IModel model;
  private IView view;

  public Controller(IModel m, IView v) {
    model = m;
    view = v;
    view.setListener(this);
    view.show();
  }

  @Override
  public void action(String e) {
    switch (e) {
      //read from the input textfield
      case "Echo Button":
        String text = view.getInputString();
        //send text to the model
        model.setString(text);

        //clear input textfield
        view.clearInputString();
        //finally echo the string in view
        text = model.getString();
        view.setEchoOutput(text);

        break;
      case "Exit Button":
        System.exit(0);
        break;
    }
  }
}
