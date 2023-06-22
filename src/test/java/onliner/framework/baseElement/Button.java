package onliner.framework.baseElement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class Button extends BaseElement {

    public Button(By locName) {
        super(locName);
    }

    @Override
    public List<Button> getElementsList() {
        List<Button> buttonsList = new ArrayList<>();
        getPresentElements();
        for (WebElement webElementFromList : webElementsList) {
            buttonsList.add(
                    new Button(locator));
        }
        return buttonsList;
    }
}
