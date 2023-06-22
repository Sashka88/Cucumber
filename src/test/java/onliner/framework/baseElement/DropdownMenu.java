package onliner.framework.baseElement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class DropdownMenu extends BaseElement{

    public DropdownMenu(By locName) {
        super(locName);
    }

    @Override
    public List<DropdownMenu> getElementsList() {
        List<DropdownMenu> dropdownMenuList = new ArrayList<>();
        getPresentElements();
        for (WebElement webElementFromList : webElementsList) {
            dropdownMenuList.add(
                    new DropdownMenu(locator));
        }
        return dropdownMenuList;
    }
}
