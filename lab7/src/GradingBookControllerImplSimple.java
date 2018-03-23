import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

/**
 * Implementation for the controller.
 */
public class GradingBookControllerImplSimple implements GradingBookController {
  final Readable in;
  final Appendable out;

  /**
   * Constructor for this class to assign in and out.
   *
   * @param in  input stream readable
   * @param out output stream appendable
   */
  public GradingBookControllerImplSimple(Readable in, Appendable out) {
    this.in = in;
    this.out = out;
  }

  /**
   * Prints menu to user to interact with.
   *
   * @param gb use to store grades
   * @throws IOException when input cannot be found
   */
  @Override
  public void menu(GradingBook gb) throws IOException {
    Objects.requireNonNull(gb);
    this.out.append("Welcome to the GradingBook!\n");
    this.out.append("\nYou can type '+' to add a grade\n");
    this.out.append("For example: '+ name score totalScore weight' to add a grade with score.\n");
    this.out.append("For example: '+ hw1 30 40 4' to add a grade hw1 with score 30, " +
            "totalScore 40 and weight 4.\n");
    this.out.append("For example: '+ name totalScore weight' to add a grade without score.\n");
    this.out.append("For example: '+ hw2 40 4' to add a grade hw2 " +
            "with totalScore 40 and weight 4.\n");
    this.out.append("\nYou can assign a score to a grade by type 'assign'\n");
    this.out.append("For example: 'assign name score' to give a grade with a new score.\n");
    this.out.append("For example: 'assign hw2 20' will give grade hw2 with new score 20.\n");
    this.out.append("\nYou can type 'divide' to divide a grade " +
            "into assignments/projects/exams categories\n");
    this.out.append("For example: 'divide name category' will give " +
            "the grade with its category.\n");
    this.out.append("For example: 'divide hw1 assignment' to " +
            "make hw1 into an assignment category.\n");
    this.out.append("For example: 'divide project1 project' to " +
            "make project1 into a project category.\n");
    this.out.append("For example: 'divide midterm exam' to " +
            "make midterm into an exam category.\n");
    this.out.append("\nYou can type 'getScore' to " +
            "get the current score for all or get each categories' current score.\n");
    this.out.append("For example: 'getScore' will give " +
            "the whole current score for all grades.\n");
    this.out.append("For example: 'getScore exam' will " +
            "give the current score for only exam category.\n");
    this.out.append("\nYou can type 'getPercent' to get how many " +
            "points of the total 100 points that has been determined with a specific score.\n");
    this.out.append("For example: 'getPercent' will give " +
            "the percentage shows how many weights are determined.\n");
    this.out.append("\nYou can type 'min' to get the " +
            "min score you can earn when you do nothing from now.\n");
    this.out.append("For example: 'min' will give the " +
            "min score you can earn.\n");
    this.out.append("\nYou can type 'max' to get the " +
            "max score you can earn when you do perfect from now.\n");
    this.out.append("For example: 'max' will give the max score you can earn.\n");
    this.out.append("\nYou can type 'quit' to quit the menu.\n");
    this.out.append("\nPlease type your choice:\n");

    String name = "";
    double score;
    double totalScore;
    double weight;
    Scanner scan = new Scanner(this.in);

    while (true) {
      String newLine = scan.nextLine();
      String[] parts = newLine.split(" ");
      if ("quit".equals(parts[0])) {
        this.out.append("\nBye!\n");
        break;
      } else if ("+".equals(parts[0])) {
        if (parts.length == 5) {
          name = parts[1];
          score = Double.parseDouble(parts[2]);
          totalScore = Double.parseDouble(parts[3]);
          weight = Double.parseDouble(parts[4]);
          gb.addGrade(name, score, totalScore, weight);
        } else if (parts.length == 4) {
          name = parts[1];
          totalScore = Double.parseDouble(parts[2]);
          weight = Double.parseDouble(parts[3]);
          gb.addGrade(name, totalScore, weight);
        } else {
          this.out.append("\nYou type the wrong command!\n");
        }
      } else if ("assign".equals(parts[0])) {
        if (parts.length == 3) {
          name = parts[1];
          score = Double.parseDouble(parts[2]);
          gb.updateGrade(name, score);
        } else {
          this.out.append("\nYou type the wrong command!\n");
        }
      } else if ("divide".equals(parts[0])) {
        name = parts[1];
        String category = parts[2];
        switch (category) {
          case "assignment":
            gb.divideGradesIntoCategories(name, GradeCategory.ASSIGNMENT);
            break;
          case "project":
            gb.divideGradesIntoCategories(name, GradeCategory.PROJECT);
            break;
          case "exam":
            gb.divideGradesIntoCategories(name, GradeCategory.EXAM);
            break;
          default:
            break;
        }
      } else if ("getScore".equals(parts[0])) {
        if (parts.length == 1) {
          this.out.append("Your current score for all is: " + gb.getCurrentScore() + " .\n");
        } else if (parts.length == 2) {
          String category = parts[1];
          switch (category) {
            case "assignment":
              this.out.append("Your current score for assignment is: " +
                      gb.getCurrentScoreWithCategory(GradeCategory.ASSIGNMENT) + " .\n");
              break;
            case "project":
              this.out.append("Your current score for project is: " +
                      gb.getCurrentScoreWithCategory(GradeCategory.PROJECT) + " .\n");
              break;
            case "exam":
              this.out.append("Your current score for exam is: " +
                      gb.getCurrentScoreWithCategory(GradeCategory.EXAM) + " .\n");
              break;
            default:
              break;
          }
        } else {
          this.out.append("\nYou type the wrong command!\n");
        }
      } else if ("getPercent".equals(parts[0])) {
        this.out.append("Your percentage of total grade that has been determined is: " +
                ((int) gb.getPercentageGradeDetermined() * 10000) / 100.0 + "% .\n");
      } else if ("min".equals(parts[0])) {
        this.out.append("Your minimum score that can be obtained in this course is: " +
                gb.getMinimumScore() + " .\n");
      } else if ("max".equals(parts[0])) {
        this.out.append("Your maximum score that can be obtained in this course is: " +
                gb.getMaximumScore() + " .\n");
      } else {
        this.out.append("\nYou type the wrong command!\n");
      }

      this.out.append("Try another command or type 'quit' to quit.\n");
    }
  }
}

