package in.hocg.app.plugins.apidoc;

/**
 * Created by hocgin on 16-12-6.
 */
public interface ApiDocResponse {
	/**
	 * @apiDefine UserNotFoundError $UserNotFoundError
	 *
	 * @apiError (UserNotFoundError) {String}  message 这只是条消息.
	 *
	 * @apiErrorExample {json} UserNotFoundError-Response:
	 *     HTTP/1.1 404 Not Found
	 *     {
	 *       "error": "UserNotFound",
	 *       "message": "this is a message"
	 *     }
	 */
}
