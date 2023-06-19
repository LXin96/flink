/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.flink.streaming.api.windowing.triggers;

/**
 * Result type for trigger methods. This determines what happens with the window, for example
 * whether the window function should be called, or the window should be discarded.
 *
 * <p>If a {@link Trigger} returns {@link #FIRE} or {@link #FIRE_AND_PURGE} but the window does not
 * contain any data the window function will not be invoked, i.e. no data will be produced for the
 * window.
 */
public enum TriggerResult {

    /** No action is taken on the window. */
    // TODO： 对这个window不做任何处理
    CONTINUE(false, false),

    /** {@code FIRE_AND_PURGE} evaluates the window function and emits the window result. */
    //TODO fire_and_purge 调用window function 并且 发出window的结果
    FIRE_AND_PURGE(true, true),

    /**
     * On {@code FIRE}, the window is evaluated and results are emitted. The window is not purged,
     * though, all elements are retained.
     */
    // TODO： 在{@code FIRE}上，计算窗口并发出结果。但是，窗口没有被清除，所有元素都被保留。
    FIRE(true, false),

    /**
     * All elements in the window are cleared and the window is discarded, without evaluating the
     * window function or emitting any elements.
     */
    // TODO： 清除窗口中的所有元素并丢弃窗口，不计算窗口函数或释放任何元素。
    PURGE(false, true);

    // ------------------------------------------------------------------------

    private final boolean fire;
    private final boolean purge;

    TriggerResult(boolean fire, boolean purge) {
        this.purge = purge;
        this.fire = fire;
    }

    public boolean isFire() {
        return fire;
    }

    public boolean isPurge() {
        return purge;
    }
}
