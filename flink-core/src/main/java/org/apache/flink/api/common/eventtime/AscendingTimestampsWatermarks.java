/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.flink.api.common.eventtime;

import org.apache.flink.annotation.Public;

import java.time.Duration;

/**
 * A watermark generator that assumes monotonically ascending timestamps within the stream split and
 * periodically generates watermarks based on that assumption.
 * TODO: 一种水印生成器，它假设在流分割中时间戳是单调递增的，并根据该假设周期性地生成水印。
 *
 * <p>The current watermark is always one after the latest (highest) timestamp, because we assume
 * that more records with the same timestamp may still follow.
 * TODO: 当前的水印总是在最新(最高)时间戳之后的一个，因为我们假设可能还会有更多具有相同时间戳的记录
 *
 * <p>The watermarks are generated periodically and tightly follow the latest timestamp in the data.
 * The delay introduced by this strategy is mainly the periodic interval in which the watermarks are
 * generated, which can be configured via {@link
 * org.apache.flink.api.common.ExecutionConfig#setAutoWatermarkInterval(long)}.
 * TODO 水印是周期性生成的，并严格遵循数据中的最新时间戳。该策略引入的延迟主要是水印生成的周期间隔，可以通过
 * TODO {@link org.apache.flink.api.common.ExecutionConfig#setAutoWatermarkInterval(long)}来配置。
 */
@Public
public class AscendingTimestampsWatermarks<T> extends BoundedOutOfOrdernessWatermarks<T> {

    /** Creates a new watermark generator with for ascending timestamps. */
    public AscendingTimestampsWatermarks() {
        super(Duration.ofMillis(0));
    }
}
