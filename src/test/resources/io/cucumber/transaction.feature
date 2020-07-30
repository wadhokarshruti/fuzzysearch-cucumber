Feature: Find the inexact customer match
Scenario Outline:
  Given multiple addresses <demographicAddresses> from the demographic API for a customer
  When customer enters <addressFromRequest> non-standard address in the request
  And cutoff is <givenCutOff>
  Then Know the match score

  Examples:
    | demographicAddresses | addressFromRequest | givenCutOff|
    |3, 90-98 South Street|Unit 3, 90-98 South Street|70|
    |3/90-98 South street|Unit 3, 90-98 South Street|70|
    |Unit 3, 90-98 South St|Unit 3, 90-98 South Street|70|
    |3, 90-98 South St|Unit 3, 90-98 South Street|70|
    |3/90-98 South st|Unit 3, 90-98 South Street|70|
    |Unit 3, 90 South Street|Unit 3, 90-98 South Street|70|
    |3, 90 South Street|Unit 3, 90-98 South Street|70|
    |3/90 South street|Unit 3, 90-98 South Street|70|
    |Unit 3, 90 South St|Unit 3, 90-98 South Street|70|
    |3, 90 South St|Unit 3, 90-98 South Street|70|
    |3/90 South St|Unit 3, 90-98 South Street|70|
    |Unit 3, 90 South Road|Unit 3, 90 South Rd|70|
    |Unit 3, 90 South Avenue|Unit 3, 90 South Ave|70|
    |167 Bongbong road|167 Bong bong road|70|
    |167Bong bong road|167 Bong bong road|70|
    |43 amaroo avenue georges hall|43 amaroo avenue, georges hall|70|
    |Unit 3, Skyline Outlook, 108 Smith Road|Unit 3, 108 Smith Road|70|
