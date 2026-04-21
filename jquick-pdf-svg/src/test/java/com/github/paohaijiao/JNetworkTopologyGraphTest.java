package com.github.paohaijiao;

import com.github.paohaijiao.combol.JNetworkTopologyData;
import com.github.paohaijiao.combol.JNetworkTopologyRenderer;
import org.junit.Test;

import java.awt.*;
import java.io.IOException;

public class JNetworkTopologyGraphTest {
    /**
     * 创建节点
     */
    private static JNetworkTopologyData.Node createNode(String id, String label, String icon, String status, String legendGroup, Color color, int radius) {
        JNetworkTopologyData.Node node = new JNetworkTopologyData.Node();
        node.setId(id);
        node.setLabel(label);
        node.setIcon(icon);
        node.setStatus(status);
        node.setLegendGroup(legendGroup);
        node.setColor(color);
        node.setRadius(radius);
        return node;
    }

    /**
     * 添加连线
     */
    private static void addLink(JNetworkTopologyData data, String sourceId, String targetId, String label, Color color, int width, boolean curved, boolean showArrow) {
        JNetworkTopologyData.Link link = new JNetworkTopologyData.Link();
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
     * 示例1：数据中心网络拓扑
     * 展示典型的数据中心三层架构（核心层、汇聚层、接入层）
     */
    @Test
    public void generateDataCenterTopology() throws IOException {
        System.out.println("\n=== 生成数据中心网络拓扑 ===");
        JNetworkTopologyData data = new JNetworkTopologyData();
        data.setTitleText("数据中心网络拓扑图");
        data.setSubtitleText("典型的三层网络架构");
        data.setFooterText("© 2025 数据中心运维团队");
        data.setWidth(1000);
        data.setHeight(700);
        data.setAutoLayout(true);
        data.setLayoutIterations(80);
        // 样式配置
        data.setCurvedLinks(false);
        data.setShowArrows(true);
        data.setShowDataFlow(true);
        data.setFlowAnimationDuration(3000);
        data.setShowGrid(true);
        data.setGridSize(40);
        data.setDefaultNodeRadius(30);
        data.setShowLegend(true);
        // 添加核心层交换机
        JNetworkTopologyData.Node coreSwitch = createNode("core-sw-01", "核心交换机", "🔵", "active", "Core Layer", new Color(233, 30, 99), 35);
        data.getNodes().add(coreSwitch);

        JNetworkTopologyData.Node coreSwitch2 = createNode("core-sw-02", "核心交换机", "🔵", "active", "Core Layer", new Color(233, 30, 99), 35);
        data.getNodes().add(coreSwitch2);
        // 添加汇聚层交换机
        String[] aggNames = {"汇聚交换机-A", "汇聚交换机-B", "汇聚交换机-C"};
        String[] aggIds = {"agg-sw-01", "agg-sw-02", "agg-sw-03"};
        Color aggColor = new Color(156, 39, 176);
        for (int i = 0; i < aggNames.length; i++) {
            JNetworkTopologyData.Node agg = createNode(aggIds[i], aggNames[i], "🟣", "active", "Aggregation Layer", aggColor, 28);
            data.getNodes().add(agg);
            // 连接到核心层
            addLink(data, "core-sw-01", aggIds[i], "10GE", new Color(66, 133, 244), 2, true, true);
            addLink(data, "core-sw-02", aggIds[i], "10GE", new Color(66, 133, 244), 2, true, true);
        }

        // 添加接入层交换机
        String[][] accessNodes = {
                {"acc-sw-01", "接入交换机-机柜A", "🟢"},
                {"acc-sw-02", "接入交换机-机柜B", "🟢"},
                {"acc-sw-03", "接入交换机-机柜C", "🟢"},
                {"acc-sw-04", "接入交换机-机柜D", "🟢"},
                {"acc-sw-05", "接入交换机-机柜E", "🟢"},
                {"acc-sw-06", "接入交换机-机柜F", "🟢"}
        };
        Color accessColor = new Color(76, 175, 80);
        for (int i = 0; i < accessNodes.length; i++) {
            JNetworkTopologyData.Node access = createNode(accessNodes[i][0], accessNodes[i][1], accessNodes[i][2], "active", "Access Layer", accessColor, 25);
            data.getNodes().add(access);
            // 连接到汇聚层（负载均衡）
            String aggId = aggIds[i % aggIds.length];
            addLink(data, aggId, accessNodes[i][0], "GE", new Color(76, 175, 80), 2, false, true);
        }
        // 添加服务器
        String[] servers = {"web-01", "Web服务器", "🌐", "app-01", "应用服务器", "⚙️", "db-01", "数据库服务器", "🗄️", "cache-01", "缓存服务器", "⚡", "storage-01", "存储服务器", "💾"};
        for (int i = 0; i < servers.length; i += 3) {
            JNetworkTopologyData.Node server = createNode(servers[i], servers[i + 1], servers[i + 2], "active", "Servers", new Color(255, 152, 0), 22);
            data.getNodes().add(server);
            // 连接到接入层
            String accessId = accessNodes[i / 3 % accessNodes.length][0];
            addLink(data, accessId, servers[i], "1GE", new Color(255, 152, 0), 1, false, true);
        }
        JOption option = new JOption();
        JTitle title = new JTitle();
        title.setText("数据中心网络拓扑");
        title.setSubtext("三层网络架构");
        option.setTitle(title);
        option.setData(data);
        JNetworkTopologyRenderer renderer = new JNetworkTopologyRenderer();
        renderer.render(option, "d://test//datacenter_topology.svg");
        System.out.println("数据中心拓扑已生成: datacenter_topology.svg");
    }

    /**
     * 示例2：云服务架构拓扑
     * 展示AWS/阿里云风格的云服务架构图
     */
    @Test
    public void generateCloudArchitectureTopology() throws IOException {
        System.out.println("\n=== 生成云服务架构拓扑 ===");
        JNetworkTopologyData data = new JNetworkTopologyData();
        data.setTitleText("云服务架构拓扑图");
        data.setSubtitleText("多区域高可用架构");
        data.setFooterText("AWS 云架构 | 生产环境");
        data.setWidth(1100);
        data.setHeight(750);
        data.setAutoLayout(true);
        data.setLayoutIterations(60);
        data.setBackgroundColor(new Color(245, 245, 250));
        // 配置样式
        data.setCurvedLinks(true);
        data.setShowArrows(true);
        data.setShowShadow(true);
        data.setShowStatus(true);
        data.setDefaultNodeRadius(28);
        data.setDefaultShape(JNetworkTopologyData.NodeShape.RECTANGLE);
        // 添加区域边界（通过不同颜色和分组体现）
        // 负载均衡器
        JNetworkTopologyData.Node lb = createNode("lb-01", "负载均衡器", "⚖️", "active", "Load Balancer", new Color(33, 150, 243), 32);
        lb.setShape(JNetworkTopologyData.NodeShape.DIAMOND);
        data.getNodes().add(lb);
        // Web服务器集群
        String[] webServers = {"web-01", "web-02", "web-03"};
        for (int i = 0; i < webServers.length; i++) {
            JNetworkTopologyData.Node web = createNode(webServers[i], "Web Server " + (i + 1), "🌐", "active", "Web Tier", new Color(76, 175, 80), 25);
            data.getNodes().add(web);
            addLink(data, "lb-01", webServers[i], "HTTP", new Color(76, 175, 80), 2, false, true);
        }

        // 应用服务器
        String[] appServers = {"app-01", "app-02"};
        for (int i = 0; i < appServers.length; i++) {
            JNetworkTopologyData.Node app = createNode(appServers[i], "App Server " + (i + 1), "⚙️", "active", "Application Tier", new Color(156, 39, 176), 25);
            data.getNodes().add(app);
            for (String web : webServers) {
                addLink(data, web, appServers[i], "RPC", new Color(156, 39, 176), 1, false, false);
            }
        }
        // 数据库主从
        JNetworkTopologyData.Node dbMaster = createNode("db-master", "主数据库", "🗄️", "active", "Database", new Color(244, 67, 54), 30);
        dbMaster.setShape(JNetworkTopologyData.NodeShape.DIAMOND);
        data.getNodes().add(dbMaster);
        JNetworkTopologyData.Node dbSlave = createNode("db-slave", "从数据库", "🗄️", "active", "Database", new Color(255, 87, 34), 28);
        data.getNodes().add(dbSlave);
        for (String app : appServers) {
            addLink(data, app, "db-master", "JDBC", new Color(244, 67, 54), 2, true, true);
        }
        addLink(data, "db-master", "db-slave", "Replication", new Color(158, 158, 158), 1, true, false);
        // 缓存服务
        JNetworkTopologyData.Node redis = createNode("redis-01", "Redis集群", "⚡", "active", "Cache", new Color(255, 193, 7), 28);
        redis.setShape(JNetworkTopologyData.NodeShape.CIRCLE);
        data.getNodes().add(redis);
        for (String app : appServers) {
            addLink(data, app, "redis-01", "Cache", new Color(255, 193, 7), 1, false, true);
        }

        // 消息队列
        JNetworkTopologyData.Node mq = createNode("mq-01", "消息队列", "📨", "active", "Messaging", new Color(233, 30, 99), 28);
        mq.setShape(JNetworkTopologyData.NodeShape.RECTANGLE);
        data.getNodes().add(mq);
        for (String app : appServers) {
            addLink(data, app, "mq-01", "AMQP", new Color(233, 30, 99), 1, false, true);
        }

        // 监控服务
        JNetworkTopologyData.Node monitor = createNode("monitor-01", "监控系统", "📊", "warning", "Monitoring", new Color(158, 158, 158), 26);
        data.getNodes().add(monitor);
        for (String node : webServers) {
            addLink(data, node, "monitor-01", "Metrics", new Color(158, 158, 158), 1, false, false);
        }
        JOption option = new JOption();
        JTitle title = new JTitle();
        title.setText("云服务架构拓扑");
        title.setSubtext("生产环境");
        option.setTitle(title);
        option.setData(data);
        JNetworkTopologyRenderer renderer = new JNetworkTopologyRenderer();
        renderer.render(option, "d://test//cloud_architecture.svg");
        System.out.println("云架构拓扑已生成: cloud_architecture.svg");
    }

    /**
     * 示例3：企业网络拓扑
     * 展示企业总部分支机构网络连接
     */
    @Test
    public void generateEnterpriseNetworkTopology() throws IOException {
        System.out.println("\n=== 生成企业网络拓扑 ===");
        JNetworkTopologyData data = new JNetworkTopologyData();
        data.setTitleText("企业网络拓扑图");
        data.setSubtitleText("总部-分支机构网络架构");
        data.setFooterText("VPN连接 | MPLS专线");
        data.setWidth(1000);
        data.setHeight(650);
        data.setAutoLayout(true);
        data.setLayoutIterations(100);
        // 总部节点
        JNetworkTopologyData.Node hq = createNode("hq", "北京总部", "🏢", "active", "Headquarters", new Color(25, 118, 210), 40);
        hq.setShape(JNetworkTopologyData.NodeShape.RECTANGLE);
        data.getNodes().add(hq);
        // 分支机构
        String[][] branches = {
                {"sh", "上海分公司", "🏭", "active"},
                {"gz", "广州分公司", "🏭", "active"},
                {"sz", "深圳分公司", "🏭", "active"},
                {"cd", "成都分公司", "🏭", "active"},
                {"wh", "武汉分公司", "🏭", "active"}
        };

        for (String[] branch : branches) {
            JNetworkTopologyData.Node branchNode = createNode(branch[0], branch[1], branch[2], branch[3], "Branches", new Color(56, 142, 60), 30);
            data.getNodes().add(branchNode);
            // 使用不同的线路类型
            if (branch[0].equals("sh") || branch[0].equals("gz")) {
                addLink(data, "hq", branch[0], "MPLS 100M", new Color(66, 133, 244), 3, true, true);
            } else {
                addLink(data, "hq", branch[0], "VPN 50M", new Color(255, 152, 0), 2, true, true);
            }
        }

        // 添加互联网出口
        JNetworkTopologyData.Node internet = createNode("internet", "互联网", "🌍", "active", "Internet", new Color(96, 125, 139), 35);
        data.getNodes().add(internet);
        addLink(data, "hq", "internet", "1Gbps", new Color(96, 125, 139), 3, true, true);
        // 添加防火墙
        JNetworkTopologyData.Node firewall = createNode("fw-01", "防火墙集群", "🔥", "active", "Security", new Color(211, 47, 47), 28);
        firewall.setShape(JNetworkTopologyData.NodeShape.DIAMOND);
        data.getNodes().add(firewall);
        addLink(data, "internet", "fw-01", "10Gbps", new Color(211, 47, 47), 2, true, false);
        addLink(data, "fw-01", "hq", "10Gbps", new Color(211, 47, 47), 2, true, false);
        JOption option = new JOption();
        JTitle title = new JTitle();
        title.setText("企业网络拓扑");
        title.setSubtext("总部-分支机构");
        option.setTitle(title);
        option.setData(data);
        JNetworkTopologyRenderer renderer = new JNetworkTopologyRenderer();
        renderer.render(option, "d://test//enterprise_network.svg");
        System.out.println("企业网络拓扑已生成: enterprise_network.svg");
    }

    /**
     * 示例4：微服务架构拓扑
     * 展示微服务之间的调用关系
     */
    @Test
    public void generateMicroserviceTopology() throws IOException {
        System.out.println("\n=== 生成微服务架构拓扑 ===");
        JNetworkTopologyData data = new JNetworkTopologyData();
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
        // API网关
        JNetworkTopologyData.Node gateway = createNode("gateway", "API Gateway", "🚪", "active", "Gateway", new Color(33, 150, 243), 32);
        gateway.setShape(JNetworkTopologyData.NodeShape.DIAMOND);
        data.getNodes().add(gateway);
        // 业务服务 - 修复数组类型问题
        // 业务服务
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
            JNetworkTopologyData.Node service = createNode(svc.id, svc.name, svc.icon, "active", "Microservices", svc.color, 26);
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
        JNetworkTopologyData.Node eureka = createNode("eureka", "服务注册中心", "📋", "active", "Infrastructure", new Color(96, 125, 139), 28);
        data.getNodes().add(eureka);
        JNetworkTopologyData.Node config = createNode("config", "配置中心", "⚙️", "active", "Infrastructure", new Color(96, 125, 139), 28);
        data.getNodes().add(config);
        for (ServiceInfo svc : services) {
            addLink(data, svc.id, "eureka", "心跳", new Color(96, 125, 139), 1, false, false);
            addLink(data, svc.id, "config", "配置拉取", new Color(96, 125, 139), 1, false, false);
        }
        // 数据库
        JNetworkTopologyData.Node db = createNode("db-cluster", "数据库集群", "🗄️", "active", "Data Layer", new Color(244, 67, 54), 30);
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
        JNetworkTopologyRenderer renderer = new JNetworkTopologyRenderer();
        renderer.render(option, "d://test//microservice_topology.svg");
        System.out.println("微服务拓扑已生成: microservice_topology.svg");
    }

    /**
     * 示例5：手动布局拓扑图
     * 展示如何精确控制每个节点的位置
     */
    @Test
    public void generateManualLayoutTopology() throws IOException {
        System.out.println("\n=== 生成手动布局拓扑图 ===");
        JNetworkTopologyData data = new JNetworkTopologyData();
        data.setTitleText("自定义布局网络拓扑");
        data.setSubtitleText("手动控制节点位置");
        data.setFooterText("网络监控系统");
        data.setWidth(900);
        data.setHeight(600);
        data.setAutoLayout(false);  // 关闭自动布局
        data.setShowGrid(true);
        data.setGridSize(30);
        data.setCurvedLinks(false);
        data.setShowArrows(true);
        // 手动指定节点位置
        int centerX = 450;
        int centerY = 250;
        // 核心交换机（中心）
        JNetworkTopologyData.Node core = createNode("core-sw", "核心交换机", "🔵", "active", "Core", new Color(233, 30, 99), 35);
        core.setX(centerX);
        core.setY(centerY);
        core.setShape(JNetworkTopologyData.NodeShape.DIAMOND);
        data.getNodes().add(core);
        // 周边设备（圆形分布）- 修复数组类型问题
        int radius = 150;
        // 设备数据分离定义
        String[] deviceIds = {"fw-01", "lb-01", "web-01", "app-01", "db-01", "cache-01"};
        String[] deviceNames = {"防火墙", "负载均衡", "Web服务器", "应用服务器", "数据库", "缓存"};
        String[] deviceIcons = {"🔥", "⚖️", "🌐", "⚙️", "🗄️", "⚡"};
        String[] deviceStatuses = {"active", "active", "active", "active", "active", "active"};
        String[] deviceGroups = {"Security", "Load Balancer", "Web Tier", "Application Tier", "Database", "Cache"};
        Color[] deviceColors = {
                new Color(211, 47, 47),   // 防火墙 - 红色
                new Color(33, 150, 243),  // 负载均衡 - 蓝色
                new Color(76, 175, 80),   // Web服务器 - 绿色
                new Color(156, 39, 176),  // 应用服务器 - 紫色
                new Color(244, 67, 54),   // 数据库 - 红色
                new Color(255, 152, 0)    // 缓存 - 橙色
        };
        for (int i = 0; i < deviceIds.length; i++) {
            double angle = 2 * Math.PI * i / deviceIds.length;
            int x = centerX + (int) (radius * Math.cos(angle));
            int y = centerY + (int) (radius * Math.sin(angle));
            JNetworkTopologyData.Node device = createNode(deviceIds[i], deviceNames[i], deviceIcons[i], deviceStatuses[i], deviceGroups[i], deviceColors[i], 28);
            device.setX(x);
            device.setY(y);
            data.getNodes().add(device);
            // 连接到核心交换机
            addLink(data, "core-sw", deviceIds[i], "10GE", deviceColors[i], 2, true, true);
        }
        // 添加外网连接
        JNetworkTopologyData.Node internet = createNode("internet", "互联网", "🌍", "active", "External", new Color(96, 125, 139), 30);
        internet.setX(centerX);
        internet.setY(centerY - radius - 80);
        data.getNodes().add(internet);
        addLink(data, "internet", "fw-01", "1Gbps", new Color(96, 125, 139), 3, true, true);
        // 添加备份链路
        addLink(data, "core-sw", "db-01", "备份链路", new Color(255, 193, 7), 2, true, false);
        JOption option = new JOption();
        JTitle title = new JTitle();
        title.setText("自定义布局拓扑");
        title.setSubtext("手动控制节点位置");
        option.setTitle(title);
        option.setData(data);
        JNetworkTopologyRenderer renderer = new JNetworkTopologyRenderer();
        renderer.render(option, "d://test//manual_layout_topology.svg");
        System.out.println("手动布局拓扑已生成: manual_layout_topology.svg");
    }
}
