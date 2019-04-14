package controllers

import javax.inject._
import play.api._
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class HomeController @Inject()(
  cc: ControllerComponents,
  loggingAction: LoggingAction
) extends AbstractController(cc) {

  def index() = loggingAction { implicit request: Request[AnyContent] =>
    Logger.logger.info("")
    Ok(views.html.index())
  }
}

class LoggingAction @Inject()(parser: BodyParsers.Default)(implicit ec: ExecutionContext)
    extends ActionBuilderImpl(parser) with Logging {
  override def invokeBlock[A](request: Request[A], block: Request[A] => Future[Result]) = {
    logger.info("calling action")
    block(request)
  }
}