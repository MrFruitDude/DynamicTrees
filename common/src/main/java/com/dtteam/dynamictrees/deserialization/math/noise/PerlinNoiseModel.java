package com.dtteam.dynamictrees.deserialization.math.noise;

import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.synth.Noise;
import net.minecraft.world.level.levelgen.synth.PerlinNoise;

import java.util.List;

public class PerlinNoiseModel implements NoiseModel {
	
	private final Noise noise;
	
	public PerlinNoiseModel(RandomSource randomSource, List<Integer> octaves) {
		this.noise = OctaveNoiseStacks.create(randomSource, octaves, PerlinNoise::new);
	}
	
	@Override
	public double sample(double x, double y, double z) {
		return noise.get(x, y, z);
	}
	
}
