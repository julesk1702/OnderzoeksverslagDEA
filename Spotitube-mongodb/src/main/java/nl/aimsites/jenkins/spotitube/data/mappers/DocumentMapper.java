package nl.aimsites.jenkins.spotitube.data.mappers;

import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.dto.*;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class DocumentMapper {

    public static PlaylistCollectionDTO documentListToPlaylistCollectionDTO(List<Document> documents) {
        List<PlaylistDTO> playlists = new ArrayList<>();
        for (Document document : documents) {
            playlists.add(documentToPlaylistDTO(document));
        }
        PlaylistDTO[] playlistArray = new PlaylistDTO[playlists.size()];
        playlistArray = playlists.toArray(playlistArray);

        PlaylistCollectionDTO playlistCollectionDTO = new PlaylistCollectionDTO(playlistArray, 0);
        return playlistCollectionDTO;
    }

    public static PlaylistDTO documentToPlaylistDTO(Document document) {
        return new PlaylistDTO(
                Integer.parseInt(document.getString("id")),
                document.getString("name"),
                false,
                null
        );
    }

    public static TrackCollectionDTO documentListToTrackCollectionDTO(List<Document> documents) {
        List<TrackDTO> tracks = new ArrayList<>();
        for (Document document : documents) {
            tracks.add(documentToTrackDTO(document));
        }
        TrackDTO[] trackArray = new TrackDTO[tracks.size()];
        trackArray = tracks.toArray(trackArray);

        TrackCollectionDTO trackCollectionDTO = new TrackCollectionDTO(trackArray);
        return trackCollectionDTO;
    }

    public static TrackDTO documentToTrackDTO(Document document) {
        return new TrackDTO(
                Integer.parseInt(document.getString("id")),
                document.getString("title"),
                document.getString("performer"),
                Integer.parseInt(document.getString("duration")),
                document.getString("album"),
                Integer.parseInt(document.getString("playcount")),
                document.getString("publicationDate"),
                document.getString("description"),
                Boolean.parseBoolean(document.getString("offlineAvailable"))
        );
    }

    public static LoginDTO documentToLoginDTO(Document document) {
        return new LoginDTO(document.getString("token"), document.getString("user"), document.getString("password"));
    }

}
