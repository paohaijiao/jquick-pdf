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
package com.github.paohaijiao.graph;

import com.github.paohaijiao.JOption;
import com.github.paohaijiao.JTitle;
import com.github.paohaijiao.adaptor.JAdaptor;
import com.github.paohaijiao.combol.JAdvancedTopologyData;
import com.github.paohaijiao.config.JGraphConfig;
import com.github.paohaijiao.config.JPdfConfig;
import com.github.paohaijiao.data.JGraphContainer;
import com.github.paohaijiao.enums.JChartType;
import com.github.paohaijiao.executor.JQuickPdfXExecutor;
import com.github.paohaijiao.resouce.JReader;
import com.github.paohaijiao.resouce.impl.JReSourceFileReader;
import org.junit.Test;

import java.awt.*;
import java.io.IOException;

/**
 * packageName com.github.paohaijiao.ele
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/11/4
 */
public class JAdvanceToplogyTest {

    @Test
    public void svg2() throws IOException {
        JGraphContainer graphContainer = new JGraphContainer();
        graphContainer.setType(JChartType.AdvancedTopology);
        JAdvancedTopologyData data = new JAdvancedTopologyData();
        data.setTitleText("微服务架构拓扑图");
        data.setSubtitleText("服务调用链路图");
        data.setFooterText("服务网格 | Istio");
        data.setWidth(1200);
        data.setHeight(800);
        data.setAutoLayout(true);
        data.setLayoutIterations(120);
        data.setCurvedLinks(true);
        data.setShowDataFlow(true);
        data.setFlowAnimationDuration(2000);
        JAdvancedTopologyData.Node gateway = createNode("gateway", "API Gateway", "🚪", "active", "Gateway", new Color(33, 150, 243), 32);
        gateway.setShape(JAdvancedTopologyData.NodeShape.DIAMOND);
        data.getNodes().add(gateway);
        class ServiceInfo {
            final String id;
            final String name;
            final String icon;
            final Color color;
            ServiceInfo(String id, String name, String icon, Color color) {
                this.id = id;
                this.name = name;
                this.icon = icon;
                this.color = color;
            }
        }

        ServiceInfo[] services = {
                new ServiceInfo("user-svc", "用户服务", "👤", new Color(76, 175, 80)),
                new ServiceInfo("order-svc", "订单服务", "📦", new Color(156, 39, 176)),
                new ServiceInfo("payment-svc", "支付服务", "💳", new Color(244, 67, 54)),
                new ServiceInfo("inventory-svc", "库存服务", "📊", new Color(255, 152, 0)),
                new ServiceInfo("notification-svc", "通知服务", "📧", new Color(3, 169, 244)),
                new ServiceInfo("product-svc", "商品服务", "🛍️", new Color(121, 85, 72)),
                new ServiceInfo("cart-svc", "购物车服务", "🛒", new Color(103, 58, 183)),
                new ServiceInfo("review-svc", "评价服务", "⭐", new Color(233, 30, 99))
        };
        for (ServiceInfo svc : services) {
            JAdvancedTopologyData.Node service = createNode(svc.id, svc.name, svc.icon, "active", "Microservices", svc.color, 26);
            data.getNodes().add(service);
            // 所有服务都通过网关
            addLink(data, "gateway", svc.id, "HTTP/gRPC", svc.color, 2, false, true);
        }

        // 添加服务间调用关系
        addLink(data, "user-svc", "order-svc", "gRPC", new Color(158, 158, 158), 1, false, false);
        addLink(data, "order-svc", "payment-svc", "HTTP", new Color(158, 158, 158), 1, false, false);
        addLink(data, "order-svc", "inventory-svc", "gRPC", new Color(158, 158, 158), 1, false, false);
        addLink(data, "order-svc", "notification-svc", "MQ", new Color(158, 158, 158), 1, false, false);
        addLink(data, "product-svc", "inventory-svc", "HTTP", new Color(158, 158, 158), 1, false, false);
        addLink(data, "cart-svc", "order-svc", "gRPC", new Color(158, 158, 158), 1, false, false);
        addLink(data, "review-svc", "product-svc", "HTTP", new Color(158, 158, 158), 1, false, false);
        // 注册中心和配置中心
        JAdvancedTopologyData.Node eureka = createNode("eureka", "服务注册中心", "📋", "active", "Infrastructure", new Color(96, 125, 139), 28);
        data.getNodes().add(eureka);
        JAdvancedTopologyData.Node configNetwork = createNode("config", "配置中心", "⚙️", "active", "Infrastructure", new Color(96, 125, 139), 28);
        data.getNodes().add(configNetwork);
        for (ServiceInfo svc : services) {
            addLink(data, svc.id, "eureka", "心跳", new Color(96, 125, 139), 1, false, false);
            addLink(data, svc.id, "config", "配置拉取", new Color(96, 125, 139), 1, false, false);
        }
        JAdvancedTopologyData.Node db = createNode("db-cluster", "数据库集群", "🗄️", "active", "Data Layer", new Color(244, 67, 54), 30);
        data.getNodes().add(db);
        for (ServiceInfo svc : services) {
            addLink(data, svc.id, "db-cluster", "JDBC", new Color(244, 67, 54), 1, false, true);
        }
        JOption option = new JOption();
        JTitle title = new JTitle();
        title.setText("微服务架构拓扑");
        title.setSubtext("服务调用链路");
        option.setTitle(title);
        option.setData(data);

        option.setData(data);
        graphContainer.setOption(option);
        JGraphConfig graphConfig = new JGraphConfig();
        graphConfig.put("svg", graphContainer);
        JPdfConfig config = new JPdfConfig();
        config.setGraphConfig(graphConfig);
        JReader fileReader = new JReSourceFileReader("sample/svg2.txt");
        JAdaptor context = new JAdaptor(fileReader);
        JQuickPdfXExecutor executor = new JQuickPdfXExecutor(config);
        executor.execute(context.getRuleContent());
    }
    /**
     * 添加连线
     */
    private static void addLink(JAdvancedTopologyData data, String sourceId, String targetId, String label, Color color, int width, boolean curved, boolean showArrow) {
        JAdvancedTopologyData.Link link = new JAdvancedTopologyData.Link();
        link.setSourceId(sourceId);
        link.setTargetId(targetId);
        link.setLabel(label);
        link.setLineColor(color);
        link.setLineWidth(width);
        link.setCurved(curved);
        link.setShowArrow(showArrow);
        link.setActive(true);
        data.getLinks().add(link);
    }
    /**
     * 创建节点
     */
    private static JAdvancedTopologyData.Node createNode(String id, String label, String icon, String status, String legendGroup, Color color, int radius) {
        JAdvancedTopologyData.Node node = new JAdvancedTopologyData.Node();
        node.setId(id);
        node.setLabel(label);
        node.setIcon(icon);
        node.setStatus(status);
        node.setLegendGroup(legendGroup);
        node.setColor(color);
        node.setRadius(radius);
        return node;
    }
}
