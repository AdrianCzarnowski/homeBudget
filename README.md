     +-----------+          +-------------+           +--------------+
     |   Users   |          |  Categories |           | Transactions |
     +-----------+          +-------------+           +--------------+
     | userId    |<---------| categoryId  |---------->| transactionId|
     | username  |          | user (FK)   |           | user (FK)   |
     | password  |          | name        |           | category (FK)|
     | email     |          | type        |           | amount       |
     | createdAt |          | description |           | type         |
     | lastLogin |          +-------------+           | transactionDate |
     +-----------+                                    | description  |
                                                      +--------------+
             +-----------------+           +---------+
             |     Budgets     |           | Reports |
             +-----------------+           +---------+
             | budgetId        |<----------| reportId|
             | user (FK)       |           | user (FK)|
             | category (FK)   |           | reportType|
             | amount          |           | startTime |
             | monthDt         |           | endTime  |
             | yearDt          |           | generatedDate |
             +-----------------+           +---------+
