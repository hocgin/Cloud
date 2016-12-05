define({ "api": [
  {
    "type": "GET",
    "url": "/index",
    "title": "测试apidoc",
    "group": "propertyManage",
    "version": "1.0.0",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "PropertyManagementMessage",
            "optional": false,
            "field": "propertyManagementMessage",
            "description": "<p>消息对象</p>"
          }
        ]
      }
    },
    "filename": "src/main/java/in/hocg/app/modules/HomeModule.java",
    "groupTitle": "propertyManage",
    "name": "GetIndex"
  }
] });