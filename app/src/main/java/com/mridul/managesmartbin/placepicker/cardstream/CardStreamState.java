package com.mridul.managesmartbin.placepicker.cardstream;


import java.util.HashSet;

/**
 * A struct object that holds the state of a  CardStreamFragment .
 */
public class CardStreamState {
    protected Card[] visibleCards;
    protected Card[] hiddenCards;
    protected HashSet<String> dismissibleCards;
    protected String shownTag;

    protected CardStreamState(Card[] visible, Card[] hidden, HashSet<String> dismissible, String shownTag) {
        visibleCards = visible;
        hiddenCards = hidden;
        dismissibleCards = dismissible;
        this.shownTag = shownTag;
    }

}
