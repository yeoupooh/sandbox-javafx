package sample;

import javafx.beans.Observable;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableBooleanValue;
import javafx.beans.value.ObservableValue;

/**
 * Created by yeoupooh on 1/24/16.
 */
public class Item {
    private final SimpleStringProperty name;
    private final SimpleStringProperty action;
    private final SimpleBooleanProperty enabled;

    public Item(String name, boolean enabled, String action) {
        this.name = new SimpleStringProperty(name);
        this.enabled = new SimpleBooleanProperty(enabled);
        this.action = new SimpleStringProperty(action);
    }

    public SimpleBooleanProperty enabledProperty() {
        return enabled;
    }

    public String getName() {
        return name.get();
    }

    public ObservableBooleanValue getEnabled() {
        return enabledProperty();
    }

    public void setEnabled(Boolean enabled) {
        enabledProperty().set(enabled);
    }

    public String getAction() {
        return action.get();
    }
}
