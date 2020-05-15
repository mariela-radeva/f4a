package vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;

public class MainVerticle extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    Router router = Router.router(vertx);
      //router.route("/").handler(StaticHandler.create());


      router.route("/").handler(routingContext -> {
          HttpServerResponse response = routingContext.response();
          response.sendFile("argon-dashboard-master/examples/login.html");
      });

      vertx.createHttpServer().requestHandler(router).listen(8888, http -> {
        if (http.succeeded()) {
          startPromise.complete();
          System.out.println("HTTP server started on port 8888");
        } else {
          startPromise.fail(http.cause());
        }
      });
  }
}
