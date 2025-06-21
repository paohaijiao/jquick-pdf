package com.paohaijiao.data.series;

/**
 * Series工厂类
 *
 * @author martin
 */
public class JSeriesFactory {

    public static JLine newLine() {
        return new JLine();
    }

    public static JLine newLine(String name) {
        return new JLine(name);
    }

    public static JLines newLines() {
        return new JLines();
    }

    public static JLines newLines(String name) {
        return new JLines(name);
    }

    public static JGauge newGauge() {
        return new JGauge();
    }

    public static JGauge newGauge(String name) {
        return new JGauge(name);
    }

    public static JBar newBar() {
        return new JBar();
    }

    public static JBar newBar(String name) {
        return new JBar(name);
    }

    public static JScatter newScatter() {
        return new JScatter();
    }

    public static JScatter newScatter(String name) {
        return new JScatter(name);
    }

    public static JEffectScatter newEffectScatter() {
        return new JEffectScatter();
    }

    public static JEffectScatter newEffectScatter(String name) {
        return new JEffectScatter(name);
    }

    public static JPie newPie() {
        return new JPie();
    }

    public static JPie newPie(String name) {
        return new JPie(name);
    }


    public static JK newK() {
        return new JK();
    }

    public static JK newK(String name) {
        return new JK(name);
    }

    public static JCandlestick newCandlestick() {
        return new JCandlestick();
    }

    public static JCandlestick newCandlestick(String name) {
        return new JCandlestick(name);
    }

    public static JFunnel newFunnel() {
        return new JFunnel();
    }

    public static JFunnel newFunnel(String name) {
        return new JFunnel(name);
    }

    public static JGraph newGraph() {
        return new JGraph();
    }

    public static JGraph newGraph(String name) {
        return new JGraph(name);
    }

    public static JTreemap newTreemap() {
        return new JTreemap();
    }

    public static JTreemap newTreemap(String name) {
        return new JTreemap(name);
    }

    public static JHeatmap newHeatmap() {
        return new JHeatmap();
    }

    public static JHeatmap newHeatmap(String name) {
        return new JHeatmap(name);
    }

    public static JMap newMap() {
        return new JMap();
    }

    public static JMap newMap(String name) {
        return new JMap(name);
    }

    public static JBoxplot newBoxplot() {
        return new JBoxplot();
    }

    public static JBoxplot newBoxplot(String name) {
        return new JBoxplot(name);
    }

    public static JParallel newParallel() {
        return new JParallel();
    }

    public static JParallel newParallel(String name) {
        return new JParallel(name);
    }

    public static JSankey newSankey() {
        return new JSankey();
    }

    public static JSankey newSankey(String name) {
        return new JSankey(name);
    }


}
