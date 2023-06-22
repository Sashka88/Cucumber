package onliner.framework.baseElement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class Checkbox extends BaseElement {

    public Checkbox(By locName) {
        super(locName);
    }

    @Override
    public List<Checkbox> getElementsList() {
        List<Checkbox> checkboxesList = new ArrayList<>();
        getPresentElements();
        for (WebElement webElementFromList : webElementsList) {
            checkboxesList.add(
                    new Checkbox(locator));
        }
        return checkboxesList;
    }
}
