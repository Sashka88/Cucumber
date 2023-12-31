package onliner.framework.baseElement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class Label extends BaseElement {

    public Label(By locName) {
        super(locName);
    }

    @Override
    public List<Label> getElementsList() {
        List<Label> labelList = new ArrayList<>();
        getPresentElements();
        for (WebElement webElementFromList : webElementsList) {
            labelList.add(
                    new Label(locator));
        }
        return labelList;
    }
}
