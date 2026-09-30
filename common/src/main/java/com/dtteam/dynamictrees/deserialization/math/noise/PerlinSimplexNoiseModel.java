package com.dtteam.dynamictrees.deserialization.math.noise;

import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.synth.Noise;
import net.minecraft.world.level.levelgen.synth.SimplexNoise;

import java.util.List;

public class PerlinSimplexNoiseModel implements NoiseModel {
	
	private final Noise noise;
	
	public PerlinSimplexNoiseModel(RandomSource randomSource, List<Integer> octaves) {
		this.noise = OctaveNoiseStacks.create(randomSource, octaves, SimplexNoise::new);
	}
	
	@Override
	public double sample(double x, double y, double z) {
		return noise.get(x, z);
	}
	
}
