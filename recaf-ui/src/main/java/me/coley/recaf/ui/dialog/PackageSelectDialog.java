package me.coley.recaf.ui.dialog;

import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import me.coley.recaf.ui.util.Icons;
import me.coley.recaf.workspace.resource.Resource;

import java.util.Set;
import java.util.TreeSet;

/**
 * Confirmation dialog that operates off of a {@link #getSelectedPackage() selected package} when completed.
 *
 * @author Matt Coley
 */
public class PackageSelectDialog extends ConfirmDialog {
	private final PackageListView packageList = new PackageListView();
	private String currentPackage;

	/**
	 * @param title
	 * 		Dialog window title.
	 * @param header
	 * 		Header text.
	 * @param graphic
	 * 		Header graphic.
	 */
	public PackageSelectDialog(String title, String header, Node graphic) {
		super(title, header, graphic);
		GridPane.setHgrow(packageList, Priority.ALWAYS);
		grid.add(packageList, 0, 0);
		grid.setPrefWidth(600);
		// Ensure confirmation is only allowed when a new value is provided.
		Node confirmButton = getDialogPane().lookupButton(confirmType);
		confirmButton.setDisable(true);
		packageList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			confirmButton.setDisable(newValue.trim().isEmpty() || newValue.equals(currentPackage));
		});
		// Window appears with package list focused.
		setOnShown(e -> packageList.requestFocus());
	}

	/**
	 * Populate the package list.
	 *
	 * @param resource
	 * 		Resource to scan for packages.
	 */
	public void populate(Resource resource) {
		// Collect packages contained in the resource
		Set<String> packages = new TreeSet<>();
		for (String className : resource.getClasses().keySet()) {
			int packageSeparator = className.lastIndexOf('/');
			if (packageSeparator > 0) {
				String packageName = className.substring(0, packageSeparator);
				packages.add(packageName);
			}
		}
		packageList.getItems().clear();
		packageList.getItems().addAll(packages);
		updateSelection();
	}

	/**
	 * Called to set the initial selection.
	 *
	 * @param currentPackage
	 * 		Original package of some item.
	 */
	public void setCurrentPackage(String currentPackage) {
		this.currentPackage = currentPackage;
		updateSelection();
	}

	/**
	 * @return Current package selected by user.
	 */
	public String getSelectedPackage() {
		return packageList.getSelectionModel().getSelectedItem();
	}

	private void updateSelection() {
		if (currentPackage != null && !packageList.getItems().isEmpty()) {
			packageList.getSelectionModel().select(currentPackage);
		}
	}

	private static class PackageListView extends ListView<String> {
		private PackageListView() {
			setCellFactory(param -> new PackageListCell());
		}
	}

	private static class PackageListCell extends ListCell<String> {
		@Override
		protected void updateItem(String item, boolean empty) {
			super.updateItem(item, empty);
			if (empty) {
				setGraphic(null);
				setText(null);
			} else {
				setGraphic(Icons.getIconView(Icons.FOLDER_PACKAGE));
				setText(item);
			}
		}
	}
}