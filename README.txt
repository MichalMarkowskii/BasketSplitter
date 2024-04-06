# Project Name: BasketSplitter

Version: 1.0

## Description:
An application for efficient division of items into delivery groups based on their available delivery methods.

## Usage:
Go to the 'target' directory where you can find the 'BasketSplitter-1.0.jar' file.
To print out the items delivery method assignments use command: java -jar <path to BasketSplitter-1.0.jar> <path to config file> <path to basket file>

Example: java -jar user/admin/BasketSplitter-1.0.jar user/admin/files/config.json user/admin/files/basket.json

The delivery groups and corresponding items are going to print out in a following fashion:
{
    "deliveryMethod1": ["item1", "item2"],
    "deliveryMethod2": ["item3", "item4"],
    ...
}

Example:
{
    "Pick-up point" : [ "Fond - Chocolate" ],
    "Courier" : [ "Cocoa Butter", "Tart - Raisin And Pecan", "Table Cloth 54x72 White", "Flower - Daisies", "Cookies - Englishbay Wht" ],
    ...
}

## Valid file format:
The application only accepts files of .json extension in format shown below.

The example format of config file:
{
    "Carrots (1kg)": ["Express Delivery", "Click&Collect"],
    "Cold Beer (330ml)": ["Express Delivery"],
    "Steak (300g)": ["Express Delivery", "Click&Collect"],
    "AA Battery (4 Pcs.)": ["Express Delivery", "Courier"],
    "Espresso Machine": ["Courier", "Click&Collect"],
    "Garden Chair": ["Courier"]
}

The example format of basket file:
[
    "Steak (300g)",
    "Carrots (1kg)",
    "Soda (24x330ml)",
    "AA Battery (4 Pcs.)",
    "Espresso Machine",
    "Garden Chair"
]

## Author:
Michał Markowski
Contact at: michoch4@gmail.com