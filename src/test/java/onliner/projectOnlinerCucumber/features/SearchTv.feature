Feature: Searching TV product according to requirements

  Scenario Outline: choose TV product according to requirements
    Given Onliner page is open
    When user navigates to menu section "Каталог"
    And user on "Каталог" page navigates from catalog section "Электроника" to subSection "Телевидение" and chooses "Телевизоры"
    And user on "Телевизоры" page selects "<tvMaker>", "<price>", "<resolution>", "<minSize>" and "<maxSize>"
    Then results must match the requirements of "<tvMaker>", "<price>", "<resolution>", "<minSize>" and "<maxSize>"


    Examples:
      | tvMaker |  | price |  | resolution |  | minSize |  | maxSize |
      | Samsung |  | 1500  |  | 1920x1080  |  | 40      |  | 50      |