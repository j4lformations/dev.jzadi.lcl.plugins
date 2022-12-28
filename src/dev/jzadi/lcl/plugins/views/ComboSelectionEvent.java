package dev.jzadi.lcl.plugins.views;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class ComboSelectionEvent {

  public static void main(String[] args) {
    Display display = new Display();
    Shell shell = new Shell(display);
    shell.setLayout(new FillLayout());

    String[] ITEMS = { "A", "B", "C", "D" };

    final Combo combo = new Combo(shell, SWT.DROP_DOWN);
    combo.setItems(ITEMS);

    combo.addSelectionListener(new SelectionListener() {
      public void widgetSelected(SelectionEvent e) {
        System.out.println("Selected index: " + combo.getSelectionIndex() + ", selected item: "
            + combo.getItem(combo.getSelectionIndex()) + ", text content in the text field: "
            + combo.getText());
      }

      public void widgetDefaultSelected(SelectionEvent e) {
        System.out.println("Default selected index: "
            + combo.getSelectionIndex()
            + ", selected item: "
            + (combo.getSelectionIndex() == -1 ? "<null>" : combo
                .getItem(combo.getSelectionIndex())) + ", text content in the text field: "
            + combo.getText());
      }
    });

    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
    display.dispose();
  }
}