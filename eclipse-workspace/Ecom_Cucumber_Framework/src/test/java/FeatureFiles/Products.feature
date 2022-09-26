Feature: Verify E-Commerce Product APIs http Request

  Scenario: Verify Login Functionality is being called successfully using LoginAPI
    Given Login Functionality Payload with "userEmail" and "userPassword"
    When user call "LoginAPI" using "Post" http request
    Then LoginAPI called got successfully with status code 200
    And Verify "message" in response body is "Login Successfully"

  Scenario Outline: Verify Functionality of addProduct is being added successfully using CreateProductAPI
    Given addProduct Payload with "<productName>","<productCategory>","<productSubCategory>","<productPrice>","<productDescription>","<productFor>" and "<productImage>"
    When user call "CreateProductAPI" using "Post" http request
    Then CreateProductAPI called got successfully with status code 201
    And Verify "message" in response body is "Product Added Successfully"
    And Stored "productId" by using "<productName>"

    Examples: 
      | productName  | productCategory | productSubCategory | productPrice | productDescription | productFor | productImage                      |
      | One Plus 10T | Electronics     | Mobiles            |        49999 | 8plus gen1         | Men-Women  | .\\Product_Image\\OnePlus.png     |
      | Redmi k50    | Electronics     | Mobiles            |        44999 | 180mega camera     | Men-Women  | .\\Product_Image\\Redmi.png       |
      | Causal Shirt | Fashion         | Cloth              |         2200 | Slim               | Men        | .\\Product_Image\\ShirtForMen.jpg |
      | SamsungTv 4k | Fashion         | Tv&Applience       |        15490 | samrt Tizen T      | Men-Women  | .\\Product_Image\\SamsungTV.jpg   |

  Scenario Outline: Verify Functionality of placeOrder is being place successfully using placeOrderAPI
    Given placeOrderProduct payload with "<country>" and productId consist with "<getNamesproduct>"
    When user call "placeOrderAPI" using "Post" http request
    Then placeOrderAPI called got successfully with status code 201
    And Verify "message" in response body is "Order Placed Successfully"

    Examples: 
      | getNamesproduct | country |
      | One Plus 10T    | Armenia |
      | Redmi k50       | Belarus |
      | Causal Shirt    | India   |
      | SamsungTv 4k    | India   |

  Scenario Outline: Verify Functionality of deleteProduct is being delete successfully using DeleteProductAPI
    Given deleteProduct payload with productId consist with "<getproductNames>"
   When user call "DeleteProductAPI" using "Delete" http request
   Then DeleteProductAPI called got successfully with status code 200
    And Verify "message" in response body is "Product Deleted Successfully"

    Examples: 
      | getproductNames |
      | One Plus 10T    |
      | Redmi k50       |
      | Causal Shirt    |
      | SamsungTv 4k    |
