/*
 * Copyright (c) 2008-2017, Hazelcast, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hazelcast.internal.metrics;

import com.hazelcast.internal.metrics.renderers.StringRenderer;

/**
 * A DoubleGauge is {link Metric} where a particular double value is read instantaneous. E.g. the current os load.
 *
 * {@link LongGauge}
 */
public interface DoubleGauge extends Metric, StringRenderer {

    /**
     * Reads the current available value as a double.
     *
     * If the underlying probe provides a long value, then the value will be converted to
     * a floating point value.
     *
     * If no probe is available, or there are problems obtaining a value from the probe, 0 is returned.
     *
     * @return the current value.
     */
    double read();
}
