package org.scalatra.example

import org.scalatra._

class TodoMvcApp extends ScalatraServlet {

  get("/") {
    <html>
      <body>
        <h1>backend</h1>
      </body>
    </html>
  }
}
