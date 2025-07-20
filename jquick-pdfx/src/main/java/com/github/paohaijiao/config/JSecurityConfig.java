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
package com.github.paohaijiao.config;

import lombok.Data;

/**
 * packageName com.github.paohaijiao.config
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/7/20
 */
@Data
public class JSecurityConfig {

    private boolean encrypted = false;

    private String userPassword;

    private String ownerPassword;

    private boolean allowPrinting = true;

    private boolean allowCopy = true;

    private boolean allowModification = false;

    private EncryptionAlgorithm algorithm = EncryptionAlgorithm.AES_256;

    public enum EncryptionAlgorithm {
        STANDARD_40, STANDARD_128, AES_128, AES_256
    }
}
