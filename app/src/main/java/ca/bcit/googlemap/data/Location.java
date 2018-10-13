package ca.bcit.googlemap.data;


import com.google.gson.annotations.SerializedName;


public class Location
{
    private String name;
    private String type;
    private Feature[] features;

    public String getName()
    {
        return name;
    }

    public String getType()
    {
        return type;
    }

    public Feature[] getFeatures()
    {
        return features;
    }

    public static class Feature
    {
        private String type;
        private Geometry geometry;
        private Properties properties;

        public String getType()
        {
            return type;
        }

        public Geometry getGeometry()
        {
            return geometry;
        }

        public Properties getProperties()
        {
            return properties;
        }

        public static class Geometry
        {
            private String type;
            private double[][][] coordinates;

            public String getType()
            {
                return type;
            }


            public double[][]  getCoordinates()
            {
                return coordinates[0];
            }

            public double[]  getFirstCoordinates()
            {
                return coordinates[0][0];
            }

        }

        public static class Properties
        {
            @SerializedName("BLDG_ID") private String buildingID;
            @SerializedName("MAPREF") private String mapRef;
            @SerializedName("Facility Name") private String facilityName;
            @SerializedName("Facility Address") private String facilityAddress;
            @SerializedName("Location") private String location;
            @SerializedName("Advance Only?") private String advanceOnly;
            @SerializedName("Local Area") private String localArea;
            @SerializedName("Accessible") private String accessible;
            @SerializedName("X") private String x;
            @SerializedName("Y") private String y;

            public String getBuildingID()
            {
                return buildingID;
            }

            public void setBuildingID(String buildingID)
            {
                this.buildingID = buildingID;
            }

            public String getMapRef()
            {
                return mapRef;
            }

            public void setMapRef(String mapRef)
            {
                this.mapRef = mapRef;
            }

            public String getFacilityName()
            {
                return facilityName;
            }

            public void setFacilityName(String facilityName)
            {
                this.facilityName = facilityName;
            }

            public String getFacilityAddress()
            {
                return facilityAddress;
            }

            public void setFacilityAddress(String facilityAddress)
            {
                this.facilityAddress = facilityAddress;
            }

            public String getLocation()
            {
                return location;
            }

            public void setLocation(String location)
            {
                this.location = location;
            }

            public String getAdvanceOnly()
            {
                return advanceOnly;
            }

            public void setAdvanceOnly(String advanceOnly)
            {
                this.advanceOnly = advanceOnly;
            }

            public String getLocalArea()
            {
                return localArea;
            }

            public void setLocalArea(String localArea)
            {
                this.localArea = localArea;
            }

            public String getAccessible()
            {
                return accessible;
            }

            public void setAccessible(String accessible)
            {
                this.accessible = accessible;
            }

            public String getX()
            {
                return x;
            }

            public void setX(String x)
            {
                this.x = x;
            }

            public String getY()
            {
                return y;
            }

            public void setY(String y)
            {
                this.y = y;
            }
        }
    }
}
