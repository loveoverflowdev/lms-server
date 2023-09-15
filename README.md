# A brief regarding API usage

## Authentication
- **Customer Register:**
  `POST: /api/v1/authentication/customer/register`
    - **Parameter**:
      ```json
      {
          "username": <String, required>,
          "phoneNumber": <String, required>,
          "password": <String, required>,
          "email": <String, required>,
      }
      ```
    - **Response**:
      ```json
      {
          "data": {
              "id": <String>,
              "username": <String>,
              "displayName": <String>,
              "email": <String>,
              "phoneNumber": <String>,
              "affilicateCode": <String>
          },
          "meta": {
              "timestamp": <Int>
          },
          "status": {
              "code": <Int>,
              "message": <String>
          }
      }
      ```
- **Customer Login:**
  `POST: /api/v1/authentication/customer/login `
    - **Parameter**:
      ```json
      {
          "usernameOrEmail": <String, required>,
          "password": <String, required>
      }
      ```
    - **Response:**
      ```json
      {
          "data": {
              "id": <String>,
              "username": <String>,
              "displayName": <String>,
              "email": <String>,
              "phoneNumber": <String>,
              "affilicateCode": <String>
          },
          "meta": {
              "timestamp": <Int>
          },
          "status": {
              "code": <Int>,
              "message": <String>
          },
          "tokens": {
              "accessToken": <String>
              "refreshToken": <String>
          }
      }
      ```

## Course
- **Top Trending Courses:**
  `GET: /api/v1/courses/top`
    - **Response:**
      ```json
      {
          "data": [
              {
                  "id": <String>,
                  "title": <String>,
                  "instructor": <String>,
                  "coverImage": <String>,
                  "description": <String>,
                  "primaryCoins": <Int>,
                  "secondaryCoins": <Int>
              }
          ],
          "meta": {
              "timestamp": <Int>
          },
          "status": {
              "code": <Int>,
              "message": <String>
          }
      }
      ```

## Customer Cart
- **Course List In Cart:**
  `GET: api/v1/customer-cart/course-list`
    - **Authorization**:
      `Brearer Token: <String>`
    - **Response**:
      ```json
      {
          "data": [
              {
                  "id": <String>,
                  "title": <String>,
                  "instructor": <String>,
                  "coverImage": <String>,
                  "description": <String>,
                  "primaryCoins": <Int>,
                  "secondaryCoins": <Int>
              }
          ],
          "meta": {
              "timestamp": <Int>
          },
          "status": {
              "code": <Int>,
              "message": <String>
          }
      }
      ```
- **Add Course To Cart:**
- **Remove Course From Cart:**

<pre>

                    ---==Lucky Coding==---
                             
    
           69696969                         69696969
        6969    696969                   696969    6969
      969    69  6969696               6969  6969     696
     969        696969696             696969696969     696
    969        69696969696           6969696969696      696
    696      9696969696969           969696969696       969
     696     696969696969             969696969        969
      696     696  96969      _=_      9696969  69    696
        9696    969696      q(-_-)p      696969    6969
           96969696         '_) (_`         69696969
              96            /__/  \            69
              69          _(<_/>  / )_         96
             6969        (__\_\_|_/__)        9696

</pre>
