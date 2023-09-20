# A brief regarding API usage

## Authentication
- **Customer Register:**
  `POST: /api/v1/authentication/customer/register`
    - **Parameter**:
      ```json5
      {
          "username": "", // string & required
          "phoneNumber": "", // string & required,
          "password": "", // string & required
          "email": "" // string & required
      }
      ```
    - **Response**:
      ```json5
      {
          "data": {
              "id": "", // string & required
              "username": "", // string & required
              "displayName": "", // string & required
              "email": "", // string & required
              "phoneNumber": "", // string & required
              "affilicateCode": "", // string & required
              "primaryCoins": 0 // integer
          },
          "meta": {
              "timestamp": 0  // integer & required
          },
          "status": {
              "code": 200, // integer & required
              "message": "" // string & required
          }
      }
      ```
- **Customer Login:**
  `POST: /api/v1/authentication/customer/login `
    - **Parameter**:
      ```json5
      {
          "usernameOrEmail": "", // string & required
          "password": "" // string & required
      }
      ```
    - **Response:**
      ```json5
      {
          "data": {
              "id": "", // string & required
              "username": "", // string & required
              "displayName": "", // string & required
              "email": "", // string & required
              "phoneNumber": "", // string & required
              "affilicateCode": "", // string & required
              "primaryCoins": 0 // integer
          },
          "meta": {
              "timestamp": 0 // integer & required
          },
          "status": {
              "code": 200, // integer & required
              "message": "", // string & required
          },
          "tokens": {
              "accessToken": "", // string & required
              "refreshToken": "" // string & required
          }
      }
      ```

## Course
- **Top Trending Courses:**
  `GET: /api/v1/courses/top`
    - **Response:**
      ```json5
      {
          "data": [
              {
                  "id": "", // string & required
                  "title": "", // string & required
                  "instructor": "", // string & required
                  "coverImage": "", // string & required
                  "description": "", // string & required
                  "primaryCoins": 0, // integer & required
                  "secondaryCoins": 0 // integer & required
              }
          ],
          "meta": {
              "timestamp": 0 // integer & required
          },
          "status": {
              "code": 200, // integer & required
              "message": "" // string & required
          }
      }
      ```
- **Course Detail:** 
`GET: /api/v1/course/{id}`
      
  - **Response:**
      ```json5
      {
          "data": {
              "id": "", // string & required
              "title": "", // string & required
              "instructor": "", // string & required
              "coverImage": "", // string & required
              "description": "", // string & required
              "primaryCoins": 0, // integer & required
              "secondaryCoins": 0 // integer & required
          },
          "meta": {
              "timestamp": 0 // integer & required
          }, 
          "status": {
              "code": 200, // integer & required
              "message": "" // string & required
          }
      }
      ```

## Customer Cart
- **Course List In Cart:**
  `GET: api/v1/customer-cart/course-list`
    - **Authorization**:
      `Brearer Token: <String>`
    - **Response**:
      ```json5
      {
          "data": [
              {
                  "id": "", // string & required
                  "title": "", // string & required
                  "instructor": "", // string & required
                  "coverImage": "", // string & required
                  "description": "", // string & required
                  "primaryCoins": 0, // integer & required
                  "secondaryCoins": 0 // integer & required
              }
          ],
          "meta": {
              "timestamp": 0 // integer & required
          },
          "status": {
              "code": 200, // integer & required
              "message": "" // string & required
          }
      }
      ```
- **Add Course To Cart:**
`POST: api/v1/customer-cart/course/{id}`
  - **Authorization**:
    `Brearer Token: <String>`
  - **Response**: 
    ```json5
    {
        "meta": {
            "timestamp": 0  // integer & required
        },
        "status": {
            "code": 200, // integer & required
            "message": "" // string & required
        }
    }
    ```

- **Remove Course From Cart:**
`DELETE: api/v1/customer-cart/course/{id}`
  - **Authorization**:
    `Brearer Token: <String>`
  - **Response**:
    ```json5
    {
        "meta": {
            "timestamp": 0  // integer & required
        },
        "status": {
            "code": 200, // integer & required
            "message": "" // string & required
        }
    }
    ```

<pre>
                Kẻ trú non cao nhìn gió thẳm
                Người xuôi sông lớn mộng kinh kỳ

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
