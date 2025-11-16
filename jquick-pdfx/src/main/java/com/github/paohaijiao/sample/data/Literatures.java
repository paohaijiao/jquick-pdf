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
package com.github.paohaijiao.sample.data;

import java.util.ArrayList;
import java.util.List;

/**
 * packageName com.github.paohaijiao.sample.data
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/7/13
 */
public class Literatures {

    private Object _id;
    private String title;
    private String journal;
    private String serialNumber;
    private String page;
    private String author;
    private String attachment;
    private boolean enabled;
    private int __v;
    private Object editLogs;
    private Object tags;

    public static List<Literatures> getData() {
        List<Literatures> literaturesList = new ArrayList<>();

        // 第一篇文献
        Literatures literature1 = new Literatures();
        literature1.set_id(null);
        literature1.setTitle("Effect of CYP2D6*10 genotype on propafenone pharmacodynamics in Chinese patients with ventricular arrhythmia.");
        literature1.setJournal("Acta Pharmacol Sin");
        literature1.setSerialNumber("2002 Nov;23(11)");
        literature1.setPage("1040-4");
        literature1.setAuthor("Cai WM, Xu J, Chen B");
        literature1.setAttachment("");
        literature1.setEnabled(true);
        literature1.set__v(0);
        literature1.setEditLogs(null);
        literature1.setTags(null);
        literaturesList.add(literature1);

        // 第二篇文献
        Literatures literature2 = new Literatures();
        literature2.set_id(null);
        literature2.setTitle("Influence of CYP2D6*10B genotype on pharmacokinetics of propafenone enantiomers in Chinese subjects.");
        literature2.setJournal("Acta Pharmacol Sin");
        literature2.setSerialNumber("2003 Dec;24(12)");
        literature2.setPage("1277-80");
        literature2.setAuthor("Chen B, Cai WM");
        literature2.setAttachment("");
        literature2.setEnabled(true);
        literature2.set__v(0);
        literature2.setEditLogs(null);
        literature2.setTags(null);
        literaturesList.add(literature2);

        return literaturesList;
    }

    public Object get_id() {
        return _id;
    }

    public void set_id(Object _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getJournal() {
        return journal;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }

    public Object getEditLogs() {
        return editLogs;
    }

    public void setEditLogs(Object editLogs) {
        this.editLogs = editLogs;
    }

    public Object getTags() {
        return tags;
    }

    public void setTags(Object tags) {
        this.tags = tags;
    }
}
