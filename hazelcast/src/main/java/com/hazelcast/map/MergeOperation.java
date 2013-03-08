/*
 * Copyright (c) 2008-2013, Hazelcast, Inc. All Rights Reserved.
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

package com.hazelcast.map;

import com.hazelcast.core.EntryView;
import com.hazelcast.map.merge.MapMergePolicy;
import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.Data;

import java.io.IOException;

public class MergeOperation extends BasePutOperation {

    private MapMergePolicy mergePolicy;
    private EntryView<Data,Data> mergingEntry;
    private transient boolean merged = false;

    public MergeOperation(String name, Data dataKey, EntryView<Data, Data> entryView, MapMergePolicy policy) {
        super(name, dataKey, null, null);
        mergingEntry = entryView;
        mergePolicy = policy;
    }

    public MergeOperation() {
    }

    public void run() {
        SimpleEntryView entryView = (SimpleEntryView) mergingEntry;
        entryView.setKey(mapService.toObject(mergingEntry.getKey()));
        entryView.setValue(mapService.toObject(mergingEntry.getValue()));
        merged = recordStore.merge(dataKey, mergingEntry, mergePolicy);
    }

    @Override
    public Object getResponse() {
        return merged;
    }

    public void afterRun() {
        invalidateNearCaches();
    }

    @Override
    protected void writeInternal(ObjectDataOutput out) throws IOException {
        super.writeInternal(out);
        out.writeObject(mergingEntry);
        out.writeObject(mergePolicy);
    }

    @Override
    protected void readInternal(ObjectDataInput in) throws IOException {
        super.readInternal(in);
        mergingEntry = in.readObject();
        mergePolicy = in.readObject();
    }

    @Override
    public String toString() {
        return "MergeOperation{" + name + "}";
    }

}
