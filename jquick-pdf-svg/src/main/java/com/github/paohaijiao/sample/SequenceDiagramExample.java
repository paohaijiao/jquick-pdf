/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Copyright (c) [2025-2099] Martin (goudingcheng@gmail.com)
 */
package com.github.paohaijiao.sample;

/**
 * packageName com.github.paohaijiao.sample
 *
 * @author Martin
 * @version 1.0.0
 * @since 2026/1/16
 */
public class SequenceDiagramExample {
    public static void main(String[] args) {
        try {
            SequenceDiagramGenerator generator = new SequenceDiagramGenerator();

            // 添加参与者
            generator.addParticipant("客户端");
            generator.addParticipant("网关");
            generator.addParticipant("认证服务");
            generator.addParticipant("用户服务");
            generator.addParticipant("数据库");

            // 添加消息序列
            generator.addMessage("客户端", "网关", "登录请求");
            generator.addMessage("网关", "认证服务", "验证token");
            generator.addMessageWithNote("认证服务", "数据库", "查询用户", "Redis缓存");
            generator.addReturnMessage("数据库", "认证服务", "返回用户数据");
            generator.addReturnMessage("认证服务", "网关", "验证通过");
            generator.addMessage("网关", "用户服务", "获取用户信息");
            generator.addMessage("用户服务", "数据库", "查询详细信息");
            generator.addReturnMessage("数据库", "用户服务", "返回详细信息");
            generator.addReturnMessage("用户服务", "网关", "返回用户信息");
            generator.addReturnMessage("网关", "客户端", "登录成功");

            // 生成 SVG 文件
            generator.generateDiagram("d://test//sequence_diagram.svg");

            System.out.println("顺序图生成完成！");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
