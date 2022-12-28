package dev.jzadi.lcl.plugins.views;

import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

public class TestCombo extends ApplicationWindow {

	/**
	 * Create the application window.
	 */
	public TestCombo() {
		super(null);
		createActions();
		addToolBar(SWT.FLAT | SWT.WRAP);
		addMenuBar();
		addStatusLine();
	}

	/**
	 * Create contents of the application window.
	 * @param parent
	 */
	@Override
	protected Control createContents(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setForeground(SWTResourceManager.getColor(255, 0, 0));
		
		// the following code is executed by the method which
		// creates the user interface
		// assumes parent is an SWT Composite

		GridLayout layout = new GridLayout(2, false);
		parent.setLayout(layout);


		Label label = new Label(parent, SWT.NONE);
		label.setText("Select a person:");
		final ComboViewer viewer = new ComboViewer(parent, SWT.READ_ONLY);

		// the ArrayContentProvider object does not store any state,
		// therefore, you can re-use instances

		viewer.setContentProvider(ArrayContentProvider.getInstance());
		viewer.setLabelProvider(new LabelProvider() {
		    @Override
		    public String getText(Object element) {
		        if (element instanceof Person) {
		            Person person = (Person) element;
		            return person.getFirstName();
		        }
		        return super.getText(element);
		    }
		});

		List<Person> persons = Arrays.asList(new Person("Lars", "Vogel"),
		        new Person("Tim", "Taler"), new Person("Jim", "Knopf"));

		// set the input of the Viewer,
		// this input is send to the content provider
		// must be called after setContentProvider

		viewer.setInput(persons);


		//react to the selection change of the viewer
		//note that the viewer returns the actual object

		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
		    @Override
		    public void selectionChanged(SelectionChangedEvent event) {
		        IStructuredSelection selection = (IStructuredSelection) event
		            .getSelection();
		        if (selection.size() > 0){
		            System.out.println(((Person) selection.getFirstElement())
		                    .getLastName());
		        }
		    }
		});
		return container;
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
	}

	/**
	 * Create the menu manager.
	 * @return the menu manager
	 */
	@Override
	protected MenuManager createMenuManager() {
		MenuManager menuManager = new MenuManager("menu");
		return menuManager;
	}

	/**
	 * Create the toolbar manager.
	 * @return the toolbar manager
	 */
	@Override
	protected ToolBarManager createToolBarManager(int style) {
		ToolBarManager toolBarManager = new ToolBarManager(style);
		return toolBarManager;
	}

	/**
	 * Create the status line manager.
	 * @return the status line manager
	 */
	@Override
	protected StatusLineManager createStatusLineManager() {
		StatusLineManager statusLineManager = new StatusLineManager();
		return statusLineManager;
	}

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			TestCombo window = new TestCombo();
			window.setBlockOnOpen(true);
			window.open();
			Display.getCurrent().dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Configure the shell.
	 * @param newShell
	 */
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("New Application");
	}

	/**
	 * Return the initial size of the window.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 300);
	}
}
