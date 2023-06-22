package onliner.framework.baseElement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class TextBox extends BaseElement {

    public TextBox(By locName) {
        super(locName);
    }

    @Override
    public List<TextBox> getElementsList() {
        List<TextBox> textBoxList = new ArrayList<>();
        getPresentElements();
        for (WebElement webElementFromList : webElementsList) {
            textBoxList.add(
                    new TextBox(locator));
        }
        return textBoxList;
    }
}
