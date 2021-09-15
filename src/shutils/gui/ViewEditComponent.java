package shutils.gui;


import java.awt.BorderLayout;
import java.util.function.BiConsumer;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;


/**
 * This class represents a component with two states: a {@code view} state and and {@code edit} state. The first one is aimed to display a certain content to the user, the second one is aimed to allows the user to edit that content. In the {@code view} state, the {@code view component} is displayed. In the {@code edit} state, the {@code edit component} will be displayed. This components also allows to specify an operation that should be executed when the state is changed.
 * @author Matteo Nardini
 *
 * @param <V> The type of the {@code view component}.
 * @param <E> The type of the {@code edit component}.
 */

public class ViewEditComponent<V extends JComponent, E extends JComponent> extends JPanel
{
	/**
	 *
	 */
	private static final long serialVersionUID = 6865636538701198220L;
	/**
	 * Contains the instance of the {@code view component}.
	 */
	private V viewElement;
	/**
	 * Contains the instance of the {@code edit component}.
	 */
	private E editElement;

	/**
	 *  Contains the instance of the panel that contains the {@code view component}.
	 */
	private JPanel viewPanel;
	/**
	 *  Contains the instance of the panel that contains the {@code edit component}.
	 */
	private JPanel editPanel;

	/**
	 * Contains the operation to be executed when changing from the {@code view} state to the {@code edit} state.
	 */
	private BiConsumer<V, E> fromViewToEdit;
	/**
	 * Contains the operation to be executed when changing from the {@code edit} state to the {@code view} state.
	 */
	private BiConsumer<V, E> fromEditToView;

	/**
	 * Creates a new view/edit component. The component is initially in the {@code view} state.
	 * @param viewElement The element to be displayed when the component is in the {@code view} state.
	 * @param editElement The element to be displayed when the component is in the {@code edit} state.
	 */
	public ViewEditComponent(V viewElement, E editElement)
	{
		this(viewElement, editElement, true);
	}

	/**
	 * Creates a new view/edit component.
	 * @param viewElement The element to be displayed when the component is in the {@code view} state.
	 * @param editElement The element to be displayed when the component is in the {@code edit} state.
	 * @param isInitialStateView States if the element is initially in the {@code view} state.
	 */
	public ViewEditComponent(V viewElement, E editElement, boolean isInitialStateView)
	{
		if ((viewElement == null) || (editElement == null))
		{
			throw new IllegalArgumentException("The viewElement and the editElement cannot be null");
		}

		this.viewElement = viewElement;
		this.editElement = editElement;

		this.viewPanel = new JPanel();
		this.viewPanel.setLayout(new BorderLayout());
		this.viewPanel.add(viewElement, BorderLayout.CENTER);
		add(this.viewPanel);

		this.editPanel = new JPanel();
		this.editPanel.setLayout(new BorderLayout());
		this.editPanel.add(editElement, BorderLayout.CENTER);
		add(this.editPanel);

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		if (isInitialStateView)
		{
			setViewState();
		} else
		{
			setEditState();
		}
	}

	/**
	 * Allows to access the instance of the {@code edit component}.
	 * @return The instance of the {@code edit component}.
	 */
	public E getEditComponent()
	{
		return this.editElement;
	}

	/**
	 * Allows to access the instance of the {@code view component}.
	 * @return The instance of the {@code view component}.
	 */
	public V getViewComponent()
	{
		return this.viewElement;
	}

	/**
	 * Allows to check if the component is in the {@code edit} state.
	 * @return The method returns {@code true} if the component is in the {@code edit} state, {@code false} otherwise.
	 */
	public boolean isEditState()
	{
		return this.editPanel.isVisible();
	}

	/**
	 * Allows to check if the component is in the {@code view} state.
	 * @return The method returns {@code true} if the component is in the {@code view} state, {@code false} otherwise.
	 */
	public boolean isViewState()
	{
		return this.viewPanel.isVisible();
	}

	/**
	 * Changes the state of the element to the {@code edit} state. If the element was already in the {@code edit} state, nothing happens.
	 */
	public void setEditState()
	{
		if ((this.fromViewToEdit != null) && isViewState())
		{
			this.fromViewToEdit.accept(this.viewElement, this.editElement);
		}

		this.viewPanel.setVisible(false);
		this.editPanel.setVisible(true);

	}

	/**
	 * Allows to set the operation to be executed when changing from the {@code edit} state to the {@code view} state.
	 * @param op The new operation to be executed when changing from the {@code edit} state to the {@code view} state. The first parameter is the instance of the {@code view component}, the second is the instance of the {@code edit component}.
	 */
	public void setEditToViewOperation(BiConsumer<V, E> op)
	{
		this.fromEditToView = op;
	}

	/**
	 * Changes the state of the element to the {@code view} state. If the element was already in the {@code view} state, nothing happens.
	 */
	public void setViewState()
	{
		if ((this.fromEditToView != null) && isEditState())
		{
			this.fromEditToView.accept(this.viewElement, this.editElement);
		}

		this.editPanel.setVisible(false);
		this.viewPanel.setVisible(true);
	}

	/**
	 * Allows to set the operation to be executed when changing from the {@code view} state to the {@code edit} state.
	 * @param op The new operation to be executed when changing from the {@code view} state to the {@code edit} state. The first parameter is the instance of the {@code view component}, the second is the instance of the {@code edit component}.
	 */
	public void setViewToEditOperation(BiConsumer<V, E> op)
	{
		this.fromViewToEdit = op;
	}
}
