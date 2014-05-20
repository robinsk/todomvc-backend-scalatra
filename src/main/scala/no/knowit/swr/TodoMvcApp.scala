package no.knowit.swr

import org.scalatra._
import org.json4s.{DefaultFormats, Formats}
import org.scalatra.json._
import no.knowit.swr.model.{Store, Todo}

class TodoMvcApp extends ScalatraServlet with JacksonJsonSupport {

  protected implicit val jsonFormats: Formats = DefaultFormats

  before() {
    contentType = formats("json")
  }

  get("/") {
    Store.getAll
  }

  post("/") {
    val todo = parsedBody.extract[Todo]
    Store.save(todo)
  }

  put("/:id") {
    val id = params("id").toLong
    val todo = parsedBody.extract[Todo]
    Store.save(id, todo)
  }

  delete("/:id") {
    val id = params("id").toLong
    Store.remove(id)
  }

}
