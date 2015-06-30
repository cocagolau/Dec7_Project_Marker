package me.dec7.marker.common.enums;

public enum ProviderType {
	Facebook,
	Twitter,
	Google,
	Marker;
	
	public static final String COLUMN_DEFINITION = "enum ('Facebook', 'Twitter', 'Google', 'Marker')";
}