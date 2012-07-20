/*
 *
 *  Copyright 2012 Netflix, Inc.
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 *
 */
package com.netflix.simianarmy.chaos;

import com.netflix.simianarmy.chaos.ChaosCrawler.InstanceGroup;

/**
 * The Interface ChaosInstanceSelector.
 */
public interface ChaosInstanceSelector {

    /**
     * Select. Pick a random instance out of the group with provided probabilty. Chaos will draw a random number and if
     * that random number is lower than probability then it will proceed to select an instance (at random) out of the
     * group. If the random number is higher than the provide probability then no instance will be selected and
     * <b>null</b> will be returned.
     *
     * The probability is the run probability. If Chaos is running hourly between 9am and 3pm with an overall configured
     * probabilty of "1.0" then the probabilty provided to this routine would be 1.0/6 (6 hours in 9am-3pm). So the
     * typical probability here would be .1666. For Chaos to select an instance it will pick a random number between 0
     * and 1. If that random number is less than the .1666 it will proced to select an instance and return it, otherwise
     * it will return null. Over 6 runs it is likely that the random number be less than .1666, but it is not certain.
     *
     * To make Chaos select an instance with 100% certainty it would have to be configured to run only once a day and
     * the instance group would have to be configured for "1.0" daily probabilty.
     *
     * @param group
     *            the group
     * @param probability
     *            the probability per run that an instance should be terminated.
     * @return the instance
     */
    String select(InstanceGroup group, double probability);
}
