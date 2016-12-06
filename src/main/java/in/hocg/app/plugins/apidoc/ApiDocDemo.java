package in.hocg.app.plugins.apidoc;

/**
 * Created by hocgin on 16-12-6.
 */
public class ApiDocDemo implements ApiDocResponse {
	
	/**
	 * @api {get post} /user/:id 请求用户信息
	 * @apiVersion 1.0.0
	 * @apiName GetUser
	 * @apiGroup User
	 * @apiDescription 这里是关于这个接口的描述
	 *
	 * @apiParam {Number} id 用户唯一ID.
	 * @apiParam {Number} [value] 可选？.
	 *
	 *
	 *
	 * @apiSuccess (200) {Number} code 响应状态.
	 * @apiSuccess (200) {Object} data  响应内容.
	 * @apiSuccess (200) {String} data.name  用户名.
	 * @apiSuccess (200) {String} message  消息描述.
	 * @apiSuccessExample {json} Success-Response:
	 *     HTTP/1.1 200 OK
	 *     {
	 *       "code": 200,
	 *       "message": "成功",
	 *       "data": {
	 *           "name": "王咋咋"
	 *       }
	 *     }
	 *
	 * @apiUse UserNotFoundError
	 * @apiError (UserNotFoundError) {String} error 错误信息
	 *
	 * @apiPermission none
	 */
	public String user(String id) {
		return null;
	}
	
}
