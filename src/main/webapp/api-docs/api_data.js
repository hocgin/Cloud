define({ "api": [
  {
    "type": "get post",
    "url": "/user/:id",
    "title": "请求用户信息",
    "version": "1.0.0",
    "name": "GetUser",
    "group": "User",
    "description": "<p>这里是关于这个接口的描述</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "id",
            "description": "<p>用户唯一ID.</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "optional": true,
            "field": "value",
            "description": "<p>可选？.</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "Number",
            "optional": false,
            "field": "code",
            "description": "<p>响应状态.</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "data",
            "description": "<p>响应内容.</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "data.name",
            "description": "<p>用户名.</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>消息描述.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK\n{\n  \"code\": 200,\n  \"message\": \"成功\",\n  \"data\": {\n      \"name\": \"王咋咋\"\n  }\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "$UserNotFoundError": [
          {
            "group": "UserNotFoundError",
            "type": "String",
            "optional": false,
            "field": "error",
            "description": "<p>错误信息</p>"
          },
          {
            "group": "UserNotFoundError",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>这只是条消息.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "UserNotFoundError-Response:",
          "content": "HTTP/1.1 404 Not Found\n{\n  \"error\": \"UserNotFound\",\n  \"message\": \"this is a message\"\n}",
          "type": "json"
        }
      ]
    },
    "permission": [
      {
        "name": "none"
      }
    ],
    "filename": "src/main/java/in/hocg/app/plugins/apidoc/ApiDocDemo.java",
    "groupTitle": "User"
  }
] });
