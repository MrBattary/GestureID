package michael.linker.gestureid.elements.view;

import android.view.View;

/**
 * Common interface for the other views.
 */
public interface IView {
    /**
     * Returns main View of the element.
     *
     * @return View object.
     */
    View getViewInstance();

    /**
     * Sets visibility for the element.
     *
     * @param visibility GONE, VISIBLE, INVISIBLE.
     */
    void setVisibility(int visibility);
}
