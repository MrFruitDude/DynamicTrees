package com.dtteam.dynamictrees.deserialization.math.noise;

import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.synth.Noise;
import net.minecraft.world.level.levelgen.synth.NoiseStack;

import java.util.List;
import java.util.TreeSet;
import java.util.function.Function;

/**
 * MC 26.3 removed the multi-octave {@code PerlinNoise.create(random, octaves)} and {@code PerlinSimplexNoise};
 * noises are now single-octave and combined through {@link NoiseStack}. This rebuilds the old octave layout:
 * octave {@code o} samples at frequency {@code 2^o}, and amplitudes halve from the lowest to the highest
 * octave, normalised by {@code 2^n - 1} over the octave span {@code n}. Values are not bit-identical to 26.2
 * (the per-octave random seeding changed), but the shape and range match.
 */
final class OctaveNoiseStacks {

    private OctaveNoiseStacks() {
    }

    static Noise create(RandomSource random, List<Integer> octaves, Function<RandomSource, Noise> octaveNoise) {
        if (octaves.isEmpty()) {
            throw new IllegalArgumentException("Need some octaves!");
        }
        TreeSet<Integer> octaveSet = new TreeSet<>(octaves);
        int first = octaveSet.first();
        int last = octaveSet.last();
        int span = last - first + 1;
        double normaliser = Math.pow(2.0, span) - 1.0;

        NoiseStack.Builder builder = NoiseStack.builder();
        for (int octave : octaveSet) {
            float amplitude = (float) (Math.pow(2.0, last - octave) / normaliser);
            builder.add(octaveNoise.apply(random), Math.pow(2.0, octave), amplitude);
        }
        return builder.build();
    }
}
